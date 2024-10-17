package com.mypg.exceptions;

public class NoSuchRoom extends Exception{
    public NoSuchRoom(){
        super("No such room");
    }
    public NoSuchRoom(String msg){
        super(msg);
    }
}
