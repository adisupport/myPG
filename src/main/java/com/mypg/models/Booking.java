package com.mypg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Booking extends BaseModel {
    @OneToOne
    Guest guest;
    @ManyToOne
    Room room;
    LocalDate checkIn;
    LocalDate checkOut;
    Double securityMoney;
    Double advanceRentPayment;
    Double rent;
}
