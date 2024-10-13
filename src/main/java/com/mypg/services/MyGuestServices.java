package com.mypg.services;


import com.mypg.dtos.GuestDTO;
import com.mypg.helper.DtoToModel;
import com.mypg.models.Guest;
import com.mypg.repo.GuestRepo;
import com.mypg.repo.ProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class MyGuestServices{
    GuestRepo guestRepo;
    ProfileRepo profileRepo;
    MyGuestServices(GuestRepo guestRepo){
        this.guestRepo = guestRepo;
    }
    public boolean addGuest(GuestDTO dto){
        Guest guest = new Guest();
        guest.setProfile(DtoToModel.getProfile(dto));
        try{
            guestRepo.save(guest);
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
