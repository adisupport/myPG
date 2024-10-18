package com.mypg.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
public class Profile extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String address;
    private Integer age;
    private String state;
    private String city;
    private int zipcode;
    private String nationality;
    private String gender;
    private LocalDate dateOfBirth;
    private String passportNumber;
    private String aadhaarNumber;
    private String occupation;
}
