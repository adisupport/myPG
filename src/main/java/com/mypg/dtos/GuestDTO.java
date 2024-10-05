package com.mypg.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestDTO {
    public String name;
    public Long number;
    public String address;
    public Long otherPhone;
}
