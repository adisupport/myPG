package com.mypg.services;

import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.repo.InvoiceRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class InvoiceService {
    InvoiceRepo repo;
    InvoiceService(InvoiceRepo repo){
        this.repo = repo;
    }
    public Invoice createInvoice(Invoice invoice){
        return repo.save(invoice);
    }
    public void createInvoice(Double amount, Double paidAmount, LocalDate paymentDate,
                                 Guest guest){
        if(guest != null){
            Invoice invoice = new Invoice();
            invoice.setGuest(guest);
            invoice.setAmount(amount);
            invoice.setPaidAmount(paidAmount);
            invoice.setPaymentDate(paymentDate);
            invoice.setGuest(guest);
            invoice.setCreatedAt(new Date());
            repo.save(invoice);
        }
    }
}
