package com.bank.dao;

import java.util.HashMap;

import com.bank.user.bean.TransactionBean;
import com.bank.user.bean.UserBean;

public interface DaoInterface {
	
	//user activities methods.
	int userAccountCreate(String accountId,UserBean userbean);
	int SignIn(String accountId,String accountPassword);
	String showBalance(String accountId);
	String Deposit(String accountId,int amount);
	String withDraw(String accountId,int amount);
	String fundTransfer(String sourceAccountId,String destinationAccountId,int amount);
	HashMap<Integer, TransactionBean> printTransactions(String accountId);
	
	//following are validation methods.
	//boolean accountIdCheck(String accountId);
	boolean validAccountId(String accountId);
	boolean checkBalance(String accountId,int amount);
	
	
}
