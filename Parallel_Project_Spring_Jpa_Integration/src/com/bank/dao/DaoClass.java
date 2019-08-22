package com.bank.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.*;

import com.bank.user.bean.UserBean;
import com.bank.user.bean.TransactionBean;

@Repository("BankDao")
public class DaoClass implements DaoInterface {
	
	@PersistenceContext(name = "persistance")
	EntityManager con;
	
	
	
	//this method create user account and data in map collection.
	@Override
	@Transactional
	public int userAccountCreate(UserBean userbean) {
			
		con.persist(userbean);
		return 1;

	}
	
	//this method return balance .
	@Override
	@Transactional
	public String showBalance(int accountId) {
		
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
	
		
		return "balance is "+ub.getBalance();


	}
	
	
	@Override
	@Transactional
	public int SignIn(int accountId, String accountPassword) {
		int flag = 0;
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		if(ub.getAccountPassword().equals(accountPassword)) {
			flag = 1;
		}
		else {
			flag = 0;
		}
		return flag;
		
	}
	
	//this method use for deposit amount. 
	@Override
	@Transactional
	public String Deposit(int accountId, int amount) {
		
		//Deposit Code
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		int updatetBalance = ub.getBalance() + amount;
		ub.setBalance(updatetBalance);
		
		
		//Transaction COde
		TransactionBean tb = new TransactionBean();
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		tb.setAmount(amount);
		tb.setTransactionType("Deposit");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		
		
		con.merge(ub);
		con.persist(tb);
		//System.out.println(ub.getTransactions());
		
		return "Deposit Successfull";

	}
	
	//this method use for withdraw amount.
	@Override
	@Transactional
	public String withDraw(int accountId, int amount) {
		
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		int updatetBalance = ub.getBalance() - amount;
		ub.setBalance(updatetBalance);
	
		TransactionBean tb = new TransactionBean();
		//Timestamp getDate = rs.getTimestamp("transactiondate");
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		tb.setAmount(amount);
		tb.setTransactionType("Withdraw");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		
		con.merge(ub);
		con.persist(tb);
		
		
		return "Withdraw Successfull";

	}
	



	//this method use for fund transfer data.
	//sourceAccountId = user one who want to transfer amount.
	//destrinationAccountId = user whom to transfer amount.
	@Override
	@Transactional
	public String fundTransfer(int sourceAccountId, int destinationAccountId, int amount) {
		
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(sourceAccountId));
		int updatetBalance = ub.getBalance() - amount;
		ub.setBalance(updatetBalance);

		
		TransactionBean tb = new TransactionBean();
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		tb.setToAccountId(destinationAccountId);
		tb.setAmount(amount);
		tb.setTransactionType("fund transfer to");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		

		
		UserBean ub2 = (UserBean) con.find(UserBean.class, new Integer(destinationAccountId));
		int updatetBalance2 = ub2.getBalance() + amount;
		ub2.setBalance(updatetBalance2);
		
		
		TransactionBean tb2 = new TransactionBean();
		
		Date date2= new Date();
		long time2 = date.getTime();
		Timestamp ts2 = new Timestamp(time2);
		tb2.setToAccountId(sourceAccountId);
		tb2.setAmount(amount);
		tb2.setTransactionType("fund transfer from");
		tb2.setTransactionDate(ts2);
		tb2.setUserbean(ub2);
		
		
		con.merge(ub);
		con.merge(ub2);
		con.persist(tb);
		con.persist(tb2);
		
		
		
		return "Transfer Successfull";
		

		
	}

	
	//this method print the all transaction of logged in user.
	@Override
	@Transactional
	public List<TransactionBean> printTransactions(int accountId){
		
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<TransactionBean> q = (TypedQuery<TransactionBean>) con.createQuery
				("SELECT t FROM TransactionBean t where accountid = ?1").setParameter(1, accountId);

		List<TransactionBean> transaction = q.getResultList();

		
		
		
		return transaction;
		
		

	}

	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////validation/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	



	
	//this method check account id is in collection or not.
	@Override
	@Transactional
	public boolean validAccountId(int accountId) {

		
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		if(ub != null) {
			return true;
		}
		else {
			return false;
		}

	}
	
	//this method check user have insufficient balance or not.
	@Override
	@Transactional
	public boolean checkBalance(int accountId, int amount) {
		
		
		UserBean ub = (UserBean) con.find(UserBean.class,accountId);
		if(ub.getBalance() >= amount) {
			return true;
		}
		else {
			return false;
		}

	}
	


}
