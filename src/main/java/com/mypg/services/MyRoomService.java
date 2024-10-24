package com.mypg.services;

import com.mypg.dtos.RoomDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomAlreadyExist;
import com.mypg.models.Guest;
import com.mypg.models.Image;
import com.mypg.models.Room;
import com.mypg.models.RoomStatus;
import com.mypg.repo.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("MyRoomService")
public class MyRoomService implements RoomService{
    private static final Logger log = LoggerFactory.getLogger(MyRoomService.class);
    RoomRepo roomRepo;
    MyRoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }


    @Override
    public void addRoom(Integer roomNumber, Integer floor, Integer noOfBeds, String type, RoomStatus status,Integer noOfEmptyBeds) throws RoomAlreadyExist {
        Optional<Room> room = roomRepo.findByNumber(roomNumber);
        if(room.isEmpty()) {
            Room newRoom = new Room();
            newRoom.setNumber(roomNumber);
            newRoom.setFloor(floor);
            newRoom.setNoOfBeds(noOfBeds);
            newRoom.setStatus(status);
            newRoom.setType(type);
            newRoom.setNoOfBedEmpty(noOfEmptyBeds);
            roomRepo.save(newRoom);
        }else{
            throw new RoomAlreadyExist("Room already exist "+ roomNumber);
        }
    }

    @Override
    public void removeRoom(Integer roomNumber) throws NoSuchElementException {
        Optional<Room> room = roomRepo.findById(roomNumber);
        if(room.isPresent()){
            Room roomToRemove = room.get();
            roomToRemove.setIsDeleted(true);
            roomRepo.save(room.get());
        }else{
            throw new NoSuchElementException("Room not found");
        }
    }

    @Override
    public void changeRoomStatus(Integer roomNumber, RoomStatus status) {
        Optional<Room> room = roomRepo.findById(roomNumber);
        if(room.isPresent()){
            Room room1 = room.get();
            room1.setStatus(status);
            roomRepo.save(room1);
        }
    }

    @Override
    public void changeRoomNumber(Integer roomNumber, Integer newRoomNumber) {
        Optional<Room> room = roomRepo.findById(roomNumber);
        if(room.isPresent()){
            Room room1 = room.get();
            room1.setNumber(newRoomNumber);
            roomRepo.save(room1);
        }
    }

    @Override
    public void changeRoomFloor(Integer roomNumber, Integer newFloor) {
        Optional<Room> room = roomRepo.findById(roomNumber);
        if(room.isPresent()){
            Room room1 = room.get();
            room1.setFloor(newFloor);
            roomRepo.save(room1);
        }
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
        Optional<Room> room = roomRepo.findById(roomNumber);
        if(room.isPresent()){
            Room room1 = room.get();
            Image img = new Image();
            img.setImgURL(imgURL);
            room1.getImages().add(img);
            roomRepo.save(room1);
        }
    }

    @Override
    public Room getRoom(Integer roomID) throws NoSuchRoom {
        Optional<Room> room = roomRepo.findById(roomID);
        if(room.isEmpty()) {
            throw new NoSuchRoom("Invalid Room Number");
        }
        return room.get();
    }

    @Override
    public void allocateGuest(Integer roomNumber,Guest guest) throws NoSuchElementException {
        Optional<Room> optionalRoom = roomRepo.findByNumber(roomNumber);
        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            if(guest!=null){
                room.getGuestList().add(guest);
                roomRepo.save(room);
            }else{
                throw new NoSuchElementException("Invalid Guest");
            }
        }else{
            throw new NoSuchElementException("Room not found");
        }
    }

    @Override
    public List<Room> getAllAvailableRooms() {
        List<Room> rooms  = roomRepo.findAll();
        List<Room> availableRooms = new ArrayList<>();
        for(Room room : rooms){
            if(room.getStatus().equals(RoomStatus.AVAILABLE)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public void updateRoom(RoomDTO dto) throws NoSuchElementException{
        Optional<Room> roomOptional = roomRepo.findByNumber(dto.getNumber());
        if(roomOptional.isPresent()){
            Room room = roomOptional.get();
            room.setRent(dto.getRent());
            room.setType(dto.getType());
            room.setFloor(dto.getFloor());
            if(dto.getStatus().equals("AVAILABLE")){
                room.setStatus(RoomStatus.AVAILABLE);
            }
            if(dto.getStatus().equals("FULL")){
                room.setStatus(RoomStatus.FULL);
            }
            if(dto.getStatus().equals("OUT OF SERVICE")){
                room.setStatus(RoomStatus.OUT_OF_SERVICE);
            }
        }

    }
}
