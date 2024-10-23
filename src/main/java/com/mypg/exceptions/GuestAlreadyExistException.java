package com.mypg.exceptions;

public class GuestAlreadyExistException extends Exception{
    public GuestAlreadyExistException(){
        super("Guest Already Exists");
    }
    public GuestAlreadyExistException(String message){
        super(message);
    }
    public GuestAlreadyExistException(Long mobile){
        super("Guest Already Exists: with mobile number " + mobile);
    }
}
