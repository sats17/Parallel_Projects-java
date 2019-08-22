package com.bank.user.bean;

import java.sql.Timestamp;

public class TransactionBean {

	private int transactionId;
	private String transactionType;
	private String toAccountId;
	private Timestamp transactionDate;
	private int amount;
	private String accountId;
	
	//private UserBean userBean;


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getToAccountId() {
		return toAccountId;
	}


	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}


	public Timestamp getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Timestamp getDate) {
		this.transactionDate = getDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	

}
