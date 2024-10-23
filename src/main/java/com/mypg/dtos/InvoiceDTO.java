package com.mypg.dtos;

import com.mypg.models.Guest;

import java.time.LocalDate;
import java.time.Month;

public class InvoiceDTO {
    private Guest guest;
    private String rentOfMonth;
    private Double roomRent;
    private Double amountPaid;
    private Double extraAmount;
    private String extraAmountNote;
    private LocalDate paymentDate;
}
