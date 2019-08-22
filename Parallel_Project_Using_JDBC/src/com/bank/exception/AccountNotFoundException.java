package com.bank.exception;

///Exception throw when account id is not found.

@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException {
public AccountNotFoundException(String message) {
	
	super(message);
	System.out.println("this is error");
	}
}
