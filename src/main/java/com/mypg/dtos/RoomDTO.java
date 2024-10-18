package com.mypg.dtos;

import com.mypg.models.Guest;
import com.mypg.models.Image;
import com.mypg.models.RoomStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
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
