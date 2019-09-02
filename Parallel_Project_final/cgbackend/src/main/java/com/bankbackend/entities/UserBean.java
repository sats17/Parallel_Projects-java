package com.bankbackend.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bankuserdata")
public class UserBean {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "autoId")//create sequence in sql.
//	@SequenceGenerator(name = "autoId", sequenceName = "mySeq")
	@Column(name = "accountid", length = 20)
	private int accountId;
	
	@Override
	public String toString() {
		return "UserBean [accountId=" + accountId + ", name=" + name + ", accountPassword=" + accountPassword
				+ ", mobileNumber=" + mobileNumber + ", Balance=" + balance +" ]";
	}

	@Column(name = "username", length = 20)
	private String name;
	@Column(name = "accountpassword", length = 20)
	private String accountPassword;
	@Column(name = "mobilenumber", length = 20)
	private String mobileNumber;
	@Column(name = "balance", length = 20)
	private int balance = 0;
	

    @OneToMany(mappedBy = "userbean", fetch = FetchType.LAZY)
    private Set<TransactionBean> transactions = new HashSet<>();

	
	public UserBean() {}
	
	public UserBean( String name, String accountPassword, String mobileNumber, int balance) {
		super();
		this.name = name;
		this.accountPassword = accountPassword;
		this.mobileNumber = mobileNumber;
		this.balance = balance;
	}
	

	@JsonIgnore
	public Set<TransactionBean> getTransactions() {
		return transactions;
	}

	@JsonIgnore
	public void addTransactions(TransactionBean transaction) {
		this.transactions.add(transaction);
		transaction.setUserbean(this);
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public int getBalance() {
		return balance;
	}

	

	public String getName() {
		return name;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	
	
	

}
