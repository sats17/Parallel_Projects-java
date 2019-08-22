package com.bank.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.*;

import com.bank.user.bean.UserBean;
import com.bank.user.bean.TransactionBean;


public class DaoClass implements DaoInterface {
	PreparedStatement ps = null;
	Connection conn = null;
	static Random rand = new Random();
	
	 HashMap<String, UserBean> UserAccountData;
	 public DaoClass() {
		 UserAccountData = new HashMap<String, UserBean>();
		}
	
	UserBean a1; // Object of Userbean class.
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");

	
	public int generateRandomTrnasactionID() {
		int n = rand.nextInt(100);
		return n;
	}
	//this method create user account and data in map collection.
	@Override
	public int userAccountCreate(String accountId, UserBean userbean) {
		// TODO Auto-generated method stub
		String accountId1 = userbean.getAccountId();
		String userName = userbean.getName();
		String userPassword = userbean.getAccountPassword();
		long mobileNumber = userbean.getMobileNumber();
		int balance = userbean.getBalance();
		
		try {
			conn = DaoDatabaseConnection.getConnection();
			String insert_str = "insert into bank values(?,?,?,?,?)";
			
			ps = conn.prepareStatement(insert_str);
			
			ps.setString(1, accountId1);
			ps.setString(2,userName);
			ps.setString(3, userPassword);
			ps.setLong(4,mobileNumber);
			ps.setInt(5,balance);
			
			int updateCount = ps.executeUpdate();
			//System.out.println(updateCount);
			conn.close();
			return updateCount;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return 0;
		}
		//UserAccountData.put(accountId, userbean);
//		return 1;
	}
	
	//this method return balance .
	@Override
	public String showBalance(String accountId) {
		
		try {
			conn = DaoDatabaseConnection.getConnection();
			String show_str = "select balance from bank where accountid = ?";
			
			ps = conn.prepareStatement(show_str);
			
			ps.setString(1, accountId);
			
			ResultSet resultSet = ps.executeQuery();
//			while ( resultSet.next() ) {
//                //S//tring lastName = ;
//                System.out.println(resultSet.getString("balance"));
//            }
			resultSet.next();
			int balance = resultSet.getInt("balance");
			return "balance is "+balance;

		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
			return "error";
		}
		//a1 = UserAccountData.get(accountId);
		//return "Balance is " + a1.getBalance();

	}
	
	
	@Override
	public int SignIn(String accountId, String accountPassword) {
		int flag = 0;
		try {
			conn = DaoDatabaseConnection.getConnection();
			String show_str = "select * from bank where accountid = ? and userpassword = ?";
			
			ps = conn.prepareStatement(show_str);
			
			ps.setString(1, accountId);
			ps.setString(2, accountPassword);
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				flag = 1;
			}
	
			return flag;
		}
		catch(Exception e) {
			System.out.println("error");
			System.out.println(e.getStackTrace());
			return 0;
		}
		
	}
	
	//this method use for deposit amount. 
	@Override
	public String Deposit(String accountId, int amount) {

		try {
			conn = DaoDatabaseConnection.getConnection();
			String dep_str = "update bank set balance = balance + ? where accountid = ?";
			
			ps = conn.prepareStatement(dep_str);
			
			ps.setInt(1, amount);
			ps.setString(2, accountId);
			
			int i = ps.executeUpdate();
			
			String insert_transaction = "insert into transaction(tid,transactiontype,transactiondate,amount,accountid) values(?,?,?,?,?)";
			
			ps = conn.prepareStatement(insert_transaction);
			
			int tid = generateRandomTrnasactionID();
			ps.setInt(1,tid);
			ps.setString(2,"Deposit");
			ps.setTimestamp(3, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			ps.setInt(4, amount);
			ps.setString(5, accountId);
			
			int j = ps.executeUpdate();
			
			//conn.close();
			return "Deposit successfull";
		}
		catch(Exception e){
			System.out.println("error");
			System.out.println(e.getStackTrace());
			return "error";
		}
	}
	
	//this method use for withdraw amount.
	@Override
	public String withDraw(String accountId, int amount) {
		try {
			conn = DaoDatabaseConnection.getConnection();
			String dep_str = "update bank set balance = balance - ? where accountid = ?";
			
			ps = conn.prepareStatement(dep_str);
			
			ps.setInt(1, amount);
			ps.setString(2, accountId);
			
			int i = ps.executeUpdate();
			
			String insert_transaction = "insert into transaction(tid,transactiontype,transactiondate,amount,accountid) values(?,?,?,?,?)";
			
			ps = conn.prepareStatement(insert_transaction);
			
			int tid = generateRandomTrnasactionID();
			ps.setInt(1,tid);
			ps.setString(2,"Withdraw");
			ps.setTimestamp(3, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			ps.setInt(4, amount);
			ps.setString(5, accountId);
			
			int j = ps.executeUpdate();
			
			//conn.close();
			return "Withdraw successfull";
		}
		catch(Exception e){
			System.out.println("error");
			System.out.println(e.getStackTrace());
			return "error";
		}
	}
	
	//this method use to sign in into account.
	//return 1 if valid ,else return 0.


	//this method use for fund transfer data.
	//sourceAccountId = user one who want to transfer amount.
	//destrinationAccountId = user whom to transfer amount.
	@Override
	public String fundTransfer(String sourceAccountId, String destinationAccountId, int amount) {
			try {
				String fund_trnsf = "update bank set balance = balance - ? where accountid = ?";
				ps = conn.prepareStatement(fund_trnsf);
				ps.setString(2, sourceAccountId);
				ps.setInt(1,amount);
				
				int i = ps.executeUpdate();
				
				
				String fund_trnsf2 = "update bank set balance = balance + ? where accountid = ?";
				ps = conn.prepareStatement(fund_trnsf2);
				ps.setString(2,destinationAccountId);
				ps.setInt(1, amount);
				
				int j = ps.executeUpdate();
				
				String insert_transaction = "insert into transaction(tid,transactiontype,toaccountid,transactiondate,amount,accountid) values(?,?,?,?,?,?)";	
				ps = conn.prepareStatement(insert_transaction);
				
				int tid = generateRandomTrnasactionID();
				ps.setInt(1,tid);
				ps.setString(2,"Fund Transfer");
				ps.setString(3, destinationAccountId);
				ps.setTimestamp(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.setInt(5, amount);
				ps.setString(6, sourceAccountId);
				
				int i2 = ps.executeUpdate();
				
				String insert_transaction2 = "insert into transaction(tid,transactiontype,toaccountid,transactiondate,amount,accountid) values(?,?,?,?,?,?)";	
				ps = conn.prepareStatement(insert_transaction2);
				
				int tid2 = generateRandomTrnasactionID();
				ps.setInt(1,tid2);
				ps.setString(2,"Fund Transfer");
				ps.setString(3, sourceAccountId);
				ps.setTimestamp(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.setInt(5, amount);
				ps.setString(6, destinationAccountId);
				
				int j2 = ps.executeUpdate();
				
//				Deposit(destinationAccountId,amount);
//				withDraw(sourceAccountId, amount);
				//conn.close();
				return "transfer successfull";
			
			}
			catch(Exception e){
				System.out.println("error");
				System.out.println(e.getMessage());
				return "error";
			}
		
	}
	
	//this method print the all transaction of logged in user.
	@Override
	public HashMap<Integer,TransactionBean> printTransactions(String accountId){
		//a1 = UserAccountData.get(accountId);
		HashMap<Integer,TransactionBean> hm = new HashMap<Integer,TransactionBean>();
		
		//System.out.println(a1.getTransaction());
		try {
			
				
			String print_transaction = "select * from transaction where accountid = ?";
			ps = conn.prepareStatement(print_transaction);
			
			ps.setString(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				TransactionBean tb = new TransactionBean();
				int tid = rs.getInt("tid");
				String transactionType = rs.getString("transactiontype");
				
				String toAccountId = rs.getString("toaccountid");
				Timestamp getDate = rs.getTimestamp("transactiondate");
				int amount = rs.getInt("amount");
				String accountId1 = rs.getString("accountId");
				
				tb.setTransactionId(tid);
				tb.setTransactionType(transactionType);
				tb.setToAccountId(toAccountId);
				tb.setAmount(amount);
				tb.setAccountId(accountId1);
				tb.setTransactionDate(getDate);
				
				hm.put(tid, tb);
			}
			//conn.close();
			return  hm;
		
		}
		catch(Exception e) {
			System.out.println("this error i got "+e.getMessage());
			return  hm;
		}
		
		

	}

	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////validation/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	



	
	//this method check account id is in collection or not.
	@Override
	public boolean validAccountId(String accountId) {
//		if (UserAccountData.containsKey(accountId)) {
//			return true;
//		} else {
//			return false;
//		}
		
		try {
			conn = DaoDatabaseConnection.getConnection();
			String val_str = "select * from bank where accountid = ?";
			
			ps = conn.prepareStatement(val_str);
			
			ps.setString(1,accountId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
			//conn.close();
			return false;
			
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//this method check user have insufficient balance or not.
	@Override
	public boolean checkBalance(String accountId, int amount) {
		try {
			conn = DaoDatabaseConnection.getConnection();
			String chk_str = "select balance from bank where accountid = ? and balance >= ?";
			
			ps = conn.prepareStatement(chk_str);
			
			ps.setString(1, accountId);
			ps.setInt(2, amount);
			
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			
			//conn.close();
			return false;
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println(e.getMessage());
			return false;
		}
	}


}
