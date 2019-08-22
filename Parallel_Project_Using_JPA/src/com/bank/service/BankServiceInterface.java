package com.bank.service;

import java.util.List;

import com.bank.user.bean.TransactionBean;

public interface BankServiceInterface {
	
	//This all service methods
	String userAccountCreate(String accountPassword,String userName,long mobileNumber);
	int SignIn(int accountId,String accountPassword);
	String showBalance(int accountId);
	String deposit(int accountId,int amount);
	String withDraw(int accountId,int amount);
	String fundTransfer(int sourceAccountId,int destinationAccountId,int amount);
	String printTransactions(int accountId);
	
	//this all validation methods.
	String validAccountId(String accountId);
	String checkBalance(String accountId,String amount);
	String nameCheck(String userName);
	String passwordCheck(String password);
	String mobileNumberCheck(String mobileNumber);
	String amountLimitCheck(String amount);
	
	
	
}