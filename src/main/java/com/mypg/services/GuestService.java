package com.mypg.services;

import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.repo.GuestRepo;

import java.util.List;

public interface GuestService {
    public List<Guest> getGuests();
    public Guest getGuest(int id);
    public boolean addGuest(Guest guest);
    public boolean updateGuest(Guest guest);
    public boolean deleteGuest(int id);
    public List<Invoice> getInvoices();
}
