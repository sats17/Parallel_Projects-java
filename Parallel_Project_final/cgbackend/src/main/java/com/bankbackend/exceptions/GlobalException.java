package com.bankbackend.exceptions;

public class GlobalException extends RuntimeException{

    public GlobalException(String msg){
        super(msg);
    }
    
    public GlobalException() {}

//    public UserNotFoundException(String msg,Throwable e){
//        super(msg,e);
//    }

	
    
}
