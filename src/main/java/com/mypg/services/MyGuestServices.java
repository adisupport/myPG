package com.mypg.services;


import com.mypg.dtos.GuestDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.models.Profile;
import com.mypg.models.Room;
import com.mypg.repo.GuestRepo;
import com.mypg.repo.ProfileRepo;
import com.mypg.repo.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MyGuestServices implements GuestService {
    private static final Logger log = LoggerFactory.getLogger(MyGuestServices.class);
    private final RoomService roomService;
    GuestRepo guestRepo;
    ProfileRepo profileRepo;

    MyGuestServices(GuestRepo guestRepo, ProfileRepo profileRepo, RoomService roomService) {
        this.profileRepo = profileRepo;
        this.guestRepo = guestRepo;
        this.roomService = roomService;
    }


    @Override
    public List<Guest> getGuests() {
        return guestRepo.findAll();
    }

    @Override
    public Optional<Guest> findGuestByMobile(Long mobile) {
        return Optional.empty();
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
    public Guest saveGuestWithRoom(GuestDTO dto,Integer roomNumber) throws NoSuchRoom {
        Room room = roomService.getRoom(roomNumber);
        Guest guest = convertDtoToGuest(dto);
        guest.setRoom(room);
        guest.setCheckIN(LocalDate.now());
        guest.setSecurityMoney(dto.getSecurityMoney());
        return guestRepo.save(guest);
    }

    @Override
    public boolean updateGuest(GuestDTO dto) {
        return false;
    }

    @Override
    public boolean deleteGuest(Long mobile) {
        return false;
    }


    @Override
    public List<Invoice> getInvoices() {
        return List.of();

    }

    @Override
    public void extendRoomValidityBy(Long mobile, Integer days)  throws NoSuchElementException {
        Optional<Guest> guestOptional= this.findGuestByMobile(mobile);
        if(guestOptional.isPresent()){
            Guest guest = guestOptional.get();
            guest.setRoomValidity(days);
            guestRepo.save(guest);
        }
        throw  new NoSuchElementException("Guest with "+mobile+", these mobile number not exist");
    }

    private void updateEntityFromDto(Guest guest, GuestDTO dto) {
        guest.setCheckIN(dto.getCheckInDate());
//        guest.setStatus(dto.getBookingStatus());
        // Update other fields as necessary
    }
    private Guest convertDtoToGuest(GuestDTO dto) {
        Guest guest = new Guest();
        guest.setMobile(dto.getMobile());
        guest.setCheckIN(dto.getCheckInDate());
        guest.setStatus("Booked");
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
        profile.setCity(dto.getCity());
        profile.setState(dto.getState());
        profile.setNationality(dto.getNationality());
        profile.setPassportNumber(dto.getPassportNumber());
        profile.setAadhaarNumber(dto.getAadhaarNumber());
        profile.setOccupation(dto.getOccupation());
        return profile;
    }

}

