package com.mypg.exceptions;

public class RoomFilledException extends Exception{
    public RoomFilledException(int roomNumber){
        super("Room number "+roomNumber+" is already filled.");
    }
}
