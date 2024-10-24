package com.mypg.dtos;

import com.mypg.models.Guest;
import com.mypg.models.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomDTO {

    private Integer number;
    private Integer floor;
    private Integer noOfBeds;
    private String type;
    private Integer noOfBedEmpty;
    private String status;
    private Double rent;
    List<Image> images;
    List<Guest> guestList;

}
