package com.bank.user.bean;

import java.util.ArrayList;
import java.util.Iterator;

public class UserBean {
	private String accountId;
	private String accountPassword;
	private String name;
	private long mobileNumber;
	private int Balance = 0;
	
	
	private ArrayList<String> tranactions = new ArrayList<String>();
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public int getBalance() {
		return Balance;
	}
	public void setBalance(int balance) {
		Balance = balance;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	public ArrayList<String> getTransaction() {
		
		return tranactions;
	}
	public void setTransaction(String transaction) {
		tranactions.add(transaction);
	}
	
	
	
}
