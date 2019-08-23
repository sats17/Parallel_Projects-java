package com.bank.dao;

import java.util.HashMap;
import java.util.List;

import com.bank.user.bean.TransactionBean;
import com.bank.user.bean.UserBean;

public interface DaoInterface {
	
	//user activities methods.
	int userAccountCreate(UserBean userbean);
	int SignIn(int accountId,String accountPassword);
	String showBalance(int accountId);
	String Deposit(int accountId,int amount);
	String withDraw(int accountId,int amount);
	String fundTransfer(int sourceAccountId,int destinationAccountId,int amount);
	List<TransactionBean> printTransactions(int accountId);
	//UserBean printTransactions(int accountId);
	//following are validation methods.
	//boolean accountIdCheck(String accountId);
	boolean validAccountId(int accountId);
	boolean checkBalance(int accountId,int amount);
	
	
}
