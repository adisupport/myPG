package com.mypg.repo;

import com.mypg.models.Guest;
import com.mypg.models.Profile;
import com.mypg.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GuestRepo extends JpaRepository<Guest,Long> {
    public Optional<Guest> findByProfile(Profile profile);
    public List<Guest> findByRoom(Room room);
    public List<Guest> findByCheckIN(Date checkIn);
    public List<Guest> findByCheckOut(Date checkOut);
    public Optional<Guest> findGuestByMobile(Long mobile);
}
