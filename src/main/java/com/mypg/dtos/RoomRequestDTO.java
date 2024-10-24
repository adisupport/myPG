package com.mypg.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomRequestDTO {
    Integer roomNumber;
    Integer floorNumber;
    Double roomRent;
    List<String> imageURL;
    String type;
}
