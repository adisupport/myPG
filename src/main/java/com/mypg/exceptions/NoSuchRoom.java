package com.mypg.exceptions;

public class NoSuchRoom extends Exception{
    public NoSuchRoom(){
        super("No such room");
    }
    public NoSuchRoom(String msg){
        super(msg);
    }
    public NoSuchRoom(Integer roomNumber){
        super("Room Number with this "+roomNumber+" does not exist");
    }
}
