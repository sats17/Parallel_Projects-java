package com.bankbackend.exceptions;

public class InvalidAuthentication extends RuntimeException {

	  public InvalidAuthentication(String msg){
	        super(msg);
	    }
	    
	    public InvalidAuthentication() {}
	
}
