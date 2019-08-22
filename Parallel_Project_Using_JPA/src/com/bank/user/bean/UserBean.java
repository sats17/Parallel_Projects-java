package com.bank.user.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bankuserdata")
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "autoId")//create sequence in sql.
	@SequenceGenerator(name = "autoId", sequenceName = "mySeq")
	@Column(name = "accountid", length = 20)
	private int accountId;
	
	@Override
	public String toString() {
		return "UserBean [accountId=" + accountId + ", name=" + name + ", accountPassword=" + accountPassword
				+ ", mobileNumber=" + mobileNumber + ", Balance=" + Balance +" ]";
	}

	@Column(name = "username", length = 20)
	private String name;
	@Column(name = "accountpassword", length = 20)
	private String accountPassword;
	@Column(name = "mobilenumber", length = 20)
	private long mobileNumber;
	@Column(name = "balance", length = 20)
	private int Balance = 0;
	


	
	public UserBean() {}
	
	public UserBean( String name, String accountPassword, long mobileNumber, int balance) {
		super();
		this.name = name;
		this.accountPassword = accountPassword;
		this.mobileNumber = mobileNumber;
		this.Balance = balance;
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



	
	
	

}
