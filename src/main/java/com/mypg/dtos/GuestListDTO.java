package com.mypg.dtos;

import com.mypg.models.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
public class GuestListDTO {
    Long id;
    Long mobile;
    Profile profile;
    LocalDate checkIN;
    LocalDate checkOut;
    GuestStatus status;
    Double securityMoney;
    LocalDate roomValidity;
    Room room;
    List<Invoice> invoices;
    Long roomValidityInDays;
    public GuestListDTO(Guest guest) {
        this.id = guest.getId();
        this.mobile = guest.getMobile();
        this.profile = guest.getProfile();
        this.checkIN = guest.getCheckIN();
        this.checkOut = guest.getCheckOut();
        this.status = guest.getStatus();
        this.securityMoney = guest.getSecurityMoney();
        this.roomValidity = guest.getRoomValidity();
        this.room = guest.getRoom();
        this.invoices = guest.getInvoices();
        this.roomValidityInDays = ChronoUnit.DAYS.between(LocalDate.now(),this.roomValidity);
    }
}
