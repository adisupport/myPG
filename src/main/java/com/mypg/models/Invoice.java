package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends BaseModel{
    @ManyToOne
    private Guest guest;
    private Double amount;
    private Month forMonth;
    private Integer year;
    private Double advance;
    private Double maintenanceCharge;
}
