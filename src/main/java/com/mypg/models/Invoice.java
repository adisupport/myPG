package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Invoice extends BaseModel{
    @ManyToOne(optional=false)
    private Guest guest;
    private Double amount;
    private Double paidAmount;
    private LocalDate paymentDate;
    private Double maintenanceCharge;
}
