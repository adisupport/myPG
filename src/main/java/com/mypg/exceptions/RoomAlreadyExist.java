package com.mypg.exceptions;

public class RoomAlreadyExist extends Exception{
    public RoomAlreadyExist(String message) {
        super("Room Already Exist: "+message);
    }
}
