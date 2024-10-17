package com.mypg.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO extends GuestDTO{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer roomNumber;
    private Double advanceRentPayment;
    private Double rent;

}
