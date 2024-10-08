package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Guest extends BaseModel{
    @OneToOne
    Profile profile;
    String name;
    LivingStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    Room room;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest")
    List<Invoice> invoices;
}
