package com.mypg.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner {
    String name;
    String address;
    String phone;
    Integer pincode;
    String email;
    String logoURL;
}
