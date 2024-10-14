package com.mypg.dtos;
import com.mypg.models.Profile;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@ToString
public class GuestDTO {
//    @NotEmpty(message = "First name is required")
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String address;
    private String state;
    private String city;
    private Integer zipcode;
    private String nationality;
    private String gender;
    private LocalDate dateOfBirth;
    private String passportNumber;
    private String aadhaarNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer roomNumber;
    private String paymentStatus;
    private String bookingStatus;
    private String occupation;

}
