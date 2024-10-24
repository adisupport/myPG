package com.mypg.services;


import com.mypg.dtos.GuestDTO;
import com.mypg.dtos.GuestResponseDTO;
import com.mypg.exceptions.InvoiceException;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomFilledException;
import com.mypg.models.*;
import com.mypg.repo.GuestRepo;
import com.mypg.repo.InvoiceRepo;
import com.mypg.repo.ProfileRepo;
import com.mypg.repo.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MyGuestServices implements GuestService {
    private final RoomService roomService;
    private final RoomRepo roomRepo;
    private final InvoiceService invoiceService;
    GuestRepo guestRepo;
    ProfileRepo profileRepo;

    MyGuestServices(GuestRepo guestRepo, ProfileRepo profileRepo,
                    RoomService roomService, RoomRepo roomRepo,
                    InvoiceService invoiceService) {
        this.profileRepo = profileRepo;
        this.guestRepo = guestRepo;
        this.roomService = roomService;
        this.roomRepo = roomRepo;
        this.invoiceService = invoiceService;
    }


    @Override
    public List<Guest> getGuests() {
        return guestRepo.findAll();
    }

    @Override
    public GuestResponseDTO findGuestByMobile(Long mobile) {
        Optional<Guest> optional = guestRepo.findGuestByMobile(mobile);
        if(optional.isPresent()) {
            Guest guest = optional.get();
            GuestResponseDTO guestDTO = new GuestResponseDTO();
            guestDTO.setInvoices(guest.getInvoices());
            guestDTO.setCheckIN(guest.getCheckIN());
            guestDTO.setMobile(guest.getMobile());
            guestDTO.setProfile(guest.getProfile());
            guestDTO.setStatus(guest.getStatus());
            guestDTO.setRoom(guest.getRoom());
            LocalDate day = guest.getRoomValidity();
            guestDTO.setRoomValidityInDays(ChronoUnit.DAYS.between(LocalDate.now(),day));
            guestDTO.setSecurityMoney(guest.getSecurityMoney());
            guest.getInvoices().sort(Comparator.comparing(Invoice::getPaymentDate));
            return guestDTO;
        }
        throw new NoSuchElementException("Guest not found");
    }


    @Override
    public boolean addGuest(GuestDTO dto) {
        Long mobile = dto.getMobile();
        Optional<Guest> guest = guestRepo.findGuestByMobile(mobile);

        if(guest.isEmpty()){
            Guest newGuest = convertDtoToGuest(dto);
            newGuest.setCheckIN(LocalDate.now());
            guestRepo.save(newGuest);
            return true;
        }
        return false;
    }

    @Override
    public Guest saveGuestWithRoom(GuestDTO dto,Integer roomNumber) throws NoSuchRoom,RoomFilledException {
        Guest guest = null;
        if(dto.getMobile()!=null){
            Optional<Guest> optional = guestRepo.findGuestByMobile(dto.getMobile());
            if(optional.isPresent()){
                guest = optional.get();
            }
        }
        Room room = roomService.getRoom(roomNumber);
        int emptyBeds = room.getNoOfBeds() - room.getGuestList().size();
        if(emptyBeds > 0){
            room.setNoOfBedEmpty(emptyBeds-1);
            if(guest == null){
                guest = convertDtoToGuest(dto);
                guest = guestRepo.save(guest);
            }
            if(emptyBeds == 1){
                room.setStatus(RoomStatus.FULL);
            }
            guest.setRoom(room);
            guest.setCheckIN(LocalDate.now());
            guest.setRoomValidity(LocalDate.now());
            guest.setSecurityMoney(dto.getSecurityMoney());
            guest.setStatus(GuestStatus.STAYING);
            return guest;
        }
        throw new RoomFilledException(roomNumber);

    }
    @Override
    public Guest getGuestByMobile(Long mobile) throws NoSuchElementException{
        Optional<Guest> optional = guestRepo.findGuestByMobile(mobile);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchElementException("Guest not found");
    }
    @Override
    public List<Guest> findGuestByRoom(Integer roomNumber) throws NoSuchRoom  {
        Optional<Room> room = roomRepo.findByNumber(roomNumber);
        if(room.isPresent()){
            return room.get().getGuestList();
        }
        throw new NoSuchRoom(roomNumber);
    }

    @Override
    public boolean deleteGuest(Long mobile) {
        Optional<Guest> optional = guestRepo.findGuestByMobile(mobile);
        if(optional.isPresent()){
            guestRepo.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public void extendRoomValidityBy(Long mobile, Integer days)  throws NoSuchElementException {
            Guest guest = guestRepo.findGuestByMobile(mobile).get();
            guest.setRoomValidity(LocalDate.now().plusDays(days));
            guestRepo.save(guest);
    }
    public List<Guest> getAllStayingGuest(){
        List<Guest> allGuests =  guestRepo.findAll();
        List<Guest> staying = new ArrayList<>();
        for(Guest guest:allGuests){
            if(guest.getStatus() == GuestStatus.STAYING){
                staying.add(guest);
            }
        }
        return staying;
    }
    public List<Guest> listOfGuestCheckOut(){
        List<Guest> allGuests =  guestRepo.findAll();
        List<Guest> checkout = new ArrayList<>();
        for(Guest guest:allGuests){
            if(guest == null || guest.getCheckOut() == null){
                continue;
            }
            if(guest.getStatus() == GuestStatus.CHECKOUT
                    && guest.getCheckOut().getMonth() == LocalDate.now().getMonth()){
                checkout.add(guest);
            }
        }
        return checkout;
    }
    public void addInvoice(Invoice invoice) throws InvoiceException {
        System.out.println("-------Add Invoice---------");
        Double amount = invoice.getAmountPaid();
        Double extraAmount = invoice.getExtraAmount();
        String extraReason = invoice.getExtraAmountNote();
        Month rentForWhichMonth = invoice.getRentForMonth();
        LocalDate paymentDate = invoice.getPaymentDate();
        Double roomRent = invoice.getRoomRent();
        String data = String.format(Locale.ENGLISH,"Received Data {Room Rent: %f, Amount Paid: %f, Payment Date: %s  }",roomRent,amount,paymentDate.toString());
        System.out.println(data);
        if(roomRent == null){
            roomRent = invoice.getGuest().getInvoices().get(0).getRoomRent();
        }
        if(extraAmount == null){
            extraAmount =  0.0;
        }
        if(roomRent + extraAmount != amount){
            throw new InvoiceException("Invoice amount is incorrect");
        }
        System.out.println("Called Create Invoice Services");
        invoiceService.createInvoice(roomRent,
                amount,
                extraAmount,
                extraReason,
                rentForWhichMonth,
                paymentDate,
                invoice.getGuest());
    }
    @Override
    public void checkout(Long mobile) throws NoSuchElementException{
        Optional<Guest> optional = guestRepo.findGuestByMobile(mobile);
        if(optional.isPresent()){
            Guest guest = optional.get();
            Room room = guest.getRoom();
            room.getGuestList().remove(guest);
            room.setNoOfBedEmpty(room.getNoOfBedEmpty()+1);
            room.setStatus(RoomStatus.AVAILABLE);
            roomRepo.save(room);
            guest.setRoom(null);
            guest.setCheckOut(LocalDate.now());
            guest.setStatus(GuestStatus.CHECKOUT);
            guestRepo.save(guest);
            return;
        }
        throw new NoSuchElementException("Guest with these "+ mobile +" mobile number not exist");
    }

    private Guest convertDtoToGuest(GuestDTO dto) {
        Guest guest = new Guest();
        guest.setMobile(dto.getMobile());
        guest.setCheckIN(dto.getCheckInDate());
        guest.setStatus(GuestStatus.STAYING);
        guest.setProfile(convertDtoToProfile(dto));
        return guest;
    }
    private Profile convertDtoToProfile(GuestDTO dto){
        Profile profile = new Profile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setMobile(dto.getMobile());
        profile.setAddress(dto.getAddress());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setAge(dto.getAge());
        profile.setCity(dto.getCity());
        profile.setState(dto.getState());
        profile.setNationality(dto.getNationality());
        profile.setPassportNumber(dto.getPassportNumber());
        profile.setAadhaarNumber(dto.getAadhaarNumber());
        profile.setOccupation(dto.getOccupation());
        return profile;
    }
}

