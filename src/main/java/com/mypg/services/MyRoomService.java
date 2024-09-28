package com.mypg.services;

import com.mypg.exceptions.RoomAlreadyExist;
import com.mypg.models.Room;
import com.mypg.models.RoomStatus;
import com.mypg.repo.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("MyRoomService")
public class MyRoomService implements RoomService{
    private static final Logger log = LoggerFactory.getLogger(MyRoomService.class);
    RoomRepo roomRepo;
    MyRoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }


    @Override
    public void addRoom(Integer roomNumber, Integer floor, Integer noOfBeds, String type, RoomStatus status) throws RoomAlreadyExist {
        Optional<Room> room = roomRepo.findByNumber(roomNumber);
        if(room.isEmpty()) {
            Room newRoom = new Room();
            newRoom.setNumber(roomNumber);
            newRoom.setFloor(floor);
            newRoom.setNoOfBeds(noOfBeds);
            newRoom.setStatus(status);
            newRoom.setType(type);
            roomRepo.save(newRoom);
        }else{
            throw new RoomAlreadyExist("Room already exist "+ roomNumber);
        }
    }

    @Override
    public void removeRoom(Integer roomNumber) {

    }

    @Override
    public void changeRoomStatus(Integer RoomNumber, RoomStatus status) {

    }

    @Override
    public void changeRoomNumber(Integer roomNumber, Integer newRoomNumber) {

    }

    @Override
    public void changeRoomFloor(Integer roomNumber, Integer newFloor) {

    }

    @Override
    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        try{
            rooms = roomRepo.findAll();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public void addImg(Integer roomNumber, String imgURL) {

    }

    @Override
    public Room getRoom(Integer roomID) {
        return null;
    }
}
