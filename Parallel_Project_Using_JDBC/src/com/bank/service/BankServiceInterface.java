package com.bank.service;

public interface BankServiceInterface {
	
	//This all service methods
	String userAccountCreate(String accountPassword,String userName,long mobileNumber);
	int SignIn(String accountId,String accountPassword);
	String showBalance(String accountId);
	String deposit(String accountId,int amount);
	String withDraw(String accountId,int amount);
	String fundTransfer(String sourceAccountId,String destinationAccountId,int amount);
	String printTransactions(String accountId);
	
	//this all validation methods.
	String validAccountId(String accountId);
	String checkBalance(String accountId,String amount);
	String nameCheck(String userName);
	String passwordCheck(String password);
	String mobileNumberCheck(String mobileNumber);
	String amountLimitCheck(String amount);
	
	
	
}