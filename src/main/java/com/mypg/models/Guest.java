package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@ToString
public class Guest  extends BaseModel{
    @Column(unique=true,nullable=false)
    Long mobile;
    @Cascade(CascadeType.PERSIST)
    @OneToOne
    Profile profile;
    private LocalDate checkIN;
    private LocalDate checkOut;
    String status;

    @ManyToOne
    Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest")
    List<Invoice> invoices;
}
