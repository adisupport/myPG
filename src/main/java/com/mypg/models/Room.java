package com.mypg.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Room{
    @Id
    private Integer number;
    private Integer floor;
    private Integer noOfBeds;
    private String type;
    private Integer noOfBedEmpty;
    private RoomStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Image> images;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room",cascade = CascadeType.ALL)
    List<Guest> guestList;
}
