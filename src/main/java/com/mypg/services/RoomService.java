package com.mypg.services;

import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomAlreadyExist;
import com.mypg.models.Guest;
import com.mypg.models.Room;
import com.mypg.models.RoomStatus;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoomService {
    void addRoom(Integer roomNumber,Integer floor, Integer noOfBeds,
                        String sharing, RoomStatus status,Integer noOfEmptyBeds) throws RoomAlreadyExist;
    void removeRoom(Integer roomNumber) throws NoSuchElementException;
    void changeRoomStatus(Integer RoomNumber,RoomStatus status);
    void changeRoomNumber(Integer roomNumber,Integer newRoomNumber);
    void changeRoomFloor(Integer roomNumber,Integer newFloor);
    List<Room> getRooms();
    void addImg(Integer roomNumber, String imgURL);
    Room getRoom(Integer roomID) throws NoSuchRoom;
    void allocateGuest(Integer roomNumber,Guest guest);
    List<Room> getAllAvailableRooms();
}
