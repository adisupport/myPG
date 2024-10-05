package com.mypg.services;

import com.mypg.models.Guest;
import com.mypg.models.Invoice;

import java.util.List;

public interface GuestService {

    public List<Guest> getAllGuest();
    public Guest addGuest(Guest guest) throws Exception;
    public Guest updateGuest(Guest guest) throws Exception;
    public void deleteGuest(Guest guest) throws Exception;
    public Guest getGuestById(int id) throws Exception;


    public List<Guest> getGuestByRoomNumber(Integer roomNumber);
    public List<Invoice> getAllInvoiceOfGuest(Guest guest);
    public void addInvoice(Guest guest, Invoice invoice) throws Exception;
}
