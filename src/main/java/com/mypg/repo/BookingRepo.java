package com.mypg.repo;

import com.mypg.models.Booking;
import com.mypg.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoom(Room room);
    List<Booking> findByCheckInBetween(LocalDate checkIn, LocalDate checkIn2);

}
