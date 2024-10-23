package com.mypg.services;

import com.mypg.exceptions.InvoiceException;
import com.mypg.models.Guest;
import com.mypg.models.GuestStatus;
import com.mypg.models.Invoice;

import com.mypg.repo.GuestRepo;

import com.mypg.repo.InvoiceRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Service
public class InvoiceService {
    private final GuestRepo guestRepo;
    private final InvoiceRepo repo;
    InvoiceService(InvoiceRepo repo, GuestRepo guestRepo){
        this.guestRepo = guestRepo;
        this.repo = repo;
    }
    public Invoice createInvoice(Invoice invoice){
        return repo.save(invoice);
    }
    public void createInvoice(Double rent, Double amountPaid,
                              Double extraAmount,
                              String extraAmountNote,
                              Month month,
                              LocalDate paymentDate,
                              Guest guest) throws InvoiceException {
        if(guest != null){
            if(guest.getStatus() == GuestStatus.CHECKOUT){
                throw new InvoiceException("Can't Create Invoice, Guest had been Leaved...");
            }
            Invoice invoice = new Invoice();
            invoice.setGuest(guest);
            invoice.setRoomRent(rent);
            invoice.setAmountPaid(amountPaid);
            invoice.setPaymentDate(paymentDate);
            invoice.setGuest(guest);
            invoice.setRentForMonth(month);
            invoice.setExtraAmount(extraAmount);
            invoice.setExtraAmountNote(extraAmountNote);
            invoice.setCreatedAt(new Date());
            repo.save(invoice);
            guest.setRoomValidity(LocalDate.now().plusMonths(1));
            guestRepo.save(guest);
        }


    }
}
