package com.mypg.dtos;

import com.mypg.models.Booking;
import com.mypg.models.Guest;
import com.mypg.models.Room;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HomeResponseDTO {
    List<Booking> bookings;
    List<Room> availableRoom;
    List<Guest> stayingGuest;
    List<Guest> checkoutGuest;
    List<Booking>allBookings;
}
