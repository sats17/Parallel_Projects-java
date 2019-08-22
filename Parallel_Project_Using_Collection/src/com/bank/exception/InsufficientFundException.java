package com.bank.exception;

//this exception throws when user have insufficient balance.

public class InsufficientFundException extends RuntimeException{
	public InsufficientFundException(String message) {
		super(message);
		}
}
