package com.mypg.services;


import com.mypg.dtos.GuestDTO;
import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.models.Profile;
import com.mypg.models.Room;
import com.mypg.repo.GuestRepo;
import com.mypg.repo.ProfileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MyGuestServices implements GuestService {
    private static final Logger log = LoggerFactory.getLogger(MyGuestServices.class);
    GuestRepo guestRepo;
    ProfileRepo profileRepo;

    MyGuestServices(GuestRepo guestRepo, ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
        this.guestRepo = guestRepo;
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

        System.out.println(dto.toString());

        Profile profile = convertDtoToProfile(dto);
        Long mobile = dto.getMobile();
        Optional<Guest> guest = guestRepo.findGuestByMobile(mobile);

        if(guest.isEmpty()){
            profile = profileRepo.save(profile);
            Guest newGuest = convertDtoToGuest(dto);
            newGuest.setProfile(profile);
            newGuest.setCheckIN(LocalDate.now());
//            newGuest.setCheckOut(LocalDate.now().plusDays(40));

//            Room room = new Room();
//            room.setNumber(0);
//            newGuest.setRoom(room);
            guestRepo.save(newGuest);
            return true;
        }
        return false;
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
    private void updateEntityFromDto(Guest guest, GuestDTO dto) {
        guest.setCheckIN(dto.getCheckInDate());
        guest.setCheckOut(dto.getCheckOutDate());
        guest.setStatus(dto.getBookingStatus());
        // Update other fields as necessary
    }
    private Guest convertDtoToGuest(GuestDTO dto) {
        Guest guest = new Guest();
        guest.setMobile(dto.getMobile());
        guest.setCheckIN(dto.getCheckInDate());
        guest.setCheckOut(dto.getCheckOutDate());
        guest.setStatus("CHECKIN");
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

        return profile;
    }

}

