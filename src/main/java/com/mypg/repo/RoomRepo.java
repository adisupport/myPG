package com.mypg.repo;

import com.mypg.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room,Integer> {
    public Optional<Room> findByNumber(Integer number);
//    public List<Room> getAll();
}
