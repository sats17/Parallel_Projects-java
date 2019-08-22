package com.bank.user.bean;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transactionuserdata")

public class TransactionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "autoTranId") //create sequence in sql.
	@SequenceGenerator(name = "autoTranId", sequenceName = "tran_seq")
	@Column(name = "transactionId",length = 20)
	private int transactionId;
	
	


	@Override
	public String toString() {
		return "TransactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", toAccountId=" + toAccountId + ", transactionDate=" + transactionDate + ", amount=" + amount
				+ ", userbean=" + userbean.getAccountId() + "]";
	}


	@Column(name = "transactionType",length = 20)
	private String transactionType;
	@Column(name = "toAccountId",length = 20)
	private int toAccountId;
	@Column(name = "transactionDate",length = 20)
	private Timestamp transactionDate;
	@Column(name = "amount",length = 20)
	private int amount;
//	@Column(name = "accountId" , length = 20)
//	private int accountId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accountid")
	private UserBean userbean;
	
	
	//private UserBean userBean;


	public UserBean getUserbean() {
		return userbean;
	}


	public void setUserbean(UserBean userbean) {
		this.userbean = userbean;
	}


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


	public int getToAccountId() {
		return toAccountId;
	}


	public void setToAccountId(int toAccountId) {
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

//
//	public int getAccountId() {
//		return accountId;
//	}
//
//
//	public void setAccountId(int accountId) {
//		this.accountId = accountId;
//	}

	
	
}
