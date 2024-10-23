package com.mypg.services;


import com.mypg.dtos.GuestDTO;
import com.mypg.dtos.GuestResponseDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomFilledException;
import com.mypg.models.*;
import com.mypg.repo.GuestRepo;
import com.mypg.repo.ProfileRepo;
import com.mypg.repo.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MyGuestServices implements GuestService {
    private final RoomService roomService;
    private final RoomRepo roomRepo;
    GuestRepo guestRepo;
    ProfileRepo profileRepo;

    MyGuestServices(GuestRepo guestRepo, ProfileRepo profileRepo, RoomService roomService, RoomRepo roomRepo) {
        this.profileRepo = profileRepo;
        this.guestRepo = guestRepo;
        this.roomService = roomService;
        this.roomRepo = roomRepo;
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
            guestDTO.setRoomValidityInDays(ChronoUnit.DAYS.between(day, LocalDate.now()));
            guestDTO.setSecurityMoney(guest.getSecurityMoney());
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

        Room room = roomService.getRoom(roomNumber);
        int emptyBeds = room.getNoOfBeds() - room.getGuestList().size();
        if(emptyBeds > 0){
            room.setNoOfBedEmpty(emptyBeds-1);
            Guest guest = convertDtoToGuest(dto);
            guest.setRoom(room);
            guest.setCheckIN(LocalDate.now());
            guest.setRoomValidity(LocalDate.now());
            guest.setSecurityMoney(dto.getSecurityMoney());
            guest.setSecurityMoney(dto.getSecurityMoney());
            guest.setStatus(GuestStatus.STAYING);
            return guestRepo.save(guest);
        }
        throw new RoomFilledException(roomNumber);

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
            if(guest.getStatus() == GuestStatus.CHECKOUT
                    && guest.getCheckOut().getMonth() == LocalDate.now().getMonth()){
                checkout.add(guest);
            }
        }
        return checkout;
    }

    @Override
    public void checkout(Long mobile) throws NoSuchElementException{
        Optional<Guest> optional = guestRepo.findGuestByMobile(mobile);
        if(optional.isPresent()){
            Guest guest = optional.get();
            guest.setRoom(null);
            guest.setStatus(GuestStatus.CHECKOUT);
            guestRepo.save(guest);
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

