package com.mypg.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private Double rent;
    private Boolean isDeleted;
    @LastModifiedDate
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Image> images;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room",cascade = CascadeType.ALL)
    List<Guest> guestList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        if(number == null){
            return super.hashCode();
        }
        return number;
    }
}
