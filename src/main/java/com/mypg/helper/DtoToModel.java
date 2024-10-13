package com.mypg.helper;

import com.mypg.dtos.GuestDTO;
import com.mypg.models.Profile;

public class DtoToModel {
    public static Profile getProfile(GuestDTO dto){
        Profile profile = new Profile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setEmail(dto.getEmail());
        profile.setAddress(dto.getAddress());
        profile.setDateOfBirth(dto.getDateOfBirth());

        return profile;
    }
}
