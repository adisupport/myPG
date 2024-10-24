package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;

@Entity
@Getter
@Setter
public class Invoice extends BaseModel{
    @ManyToOne(optional=false)
    private Guest guest;
    private Month rentForMonth;
    private Double roomRent;
    private Double amountPaid;
    private Double extraAmount;
    private String extraAmountNote;
    private LocalDate paymentDate;


}
