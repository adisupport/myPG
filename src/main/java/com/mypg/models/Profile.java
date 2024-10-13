package com.mypg.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Profile{
    private String firstName;
    private String lastName;
    private String email;
    @Id
    private Long mobile;
    private String address;
    private String state;
    private String city;
    private int zipcode;
    private String nationality;
    private String gender;
    private Date dateOfBirth;
    private String passportNumber;
    private String aadhaarNumber;
    @OneToOne
    User user;

}
