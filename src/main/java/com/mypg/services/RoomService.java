package com.mypg.services;

import com.mypg.models.Room;
import com.mypg.models.RoomStatus;

import java.util.List;

public interface RoomService {
    public void addRoom(Integer roomNumber,Integer floor, Integer noOfBeds,
                        String sharing, RoomStatus status);
    public void removeRoom(Integer roomNumber);
    public void changeRoomStatus(Integer RoomNumber,RoomStatus status);
    public void changeRoomNumber(Integer roomNumber,Integer newRoomNumber);
    public void changeRoomFloor(Integer roomNumber,Integer newFloor);
    public List<Room> getRooms();
    public void addImg(Integer roomNumber, String imgURL);
    public Room getRoom(Integer roomID);
}
