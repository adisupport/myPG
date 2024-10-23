package com.mypg.exceptions;

public class InvoiceException extends Exception{
    public InvoiceException(){
        super("InvoiceException");
    }
    public InvoiceException(String message){
        super(message);
    }
}
