package com.bank.exception;

///Exception throw when account id is not found.

public class AccountNotFoundException extends RuntimeException {
public AccountNotFoundException(String message) {
	super(message);
	}
}
