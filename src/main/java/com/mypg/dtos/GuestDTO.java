package com.mypg.dtos;
import com.mypg.models.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class GuestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String address;
    private String nationality;
    private String passportNumber;
    private String gender;
    private Date dateOfBirth;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer roomNumber;
    private String paymentStatus;
    private String bookingStatus;

}
