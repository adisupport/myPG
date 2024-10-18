package com.mypg.services;

import com.mypg.dtos.GuestDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomFilledException;
import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.repo.GuestRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public interface GuestService {
    public List<Guest> getGuests();
    public Optional<Guest> findGuestByMobile(Long mobile);
    public boolean addGuest(GuestDTO dto);
    public Guest saveGuestWithRoom(GuestDTO dto,Integer roomNumber) throws NoSuchRoom, RoomFilledException;
    public List<Guest> findGuestByRoom(Integer roomNumber) throws NoSuchRoom;
    public boolean deleteGuest(Long mobile);
    public void extendRoomValidityBy(Long mobile,Integer days) throws NoSuchElementException;
    public void checkout(Long mobile);
    public List<Guest> listOfGuestCheckOut();
    public List<Guest> getAllStayingGuest();
}
