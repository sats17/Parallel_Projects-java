package com.bank.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.text.*;

import com.bank.user.bean.UserBean;


public class DaoClass implements DaoInterface {
	 HashMap<String, UserBean> UserAccountData;
	 public DaoClass() {
		 UserAccountData = new HashMap<String, UserBean>();
		}
	
	UserBean a1; // Object of Userbean class.
	
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");

	//this method create user account and data in map collection.
	@Override
	public int userAccountCreate(String accountId, UserBean userbean) {
		// TODO Auto-generated method stub
		UserAccountData.put(accountId, userbean);
		return 1;
	}
	
	//this method return balance .
	@Override
	public String showBalance(String accountId) {

		a1 = UserAccountData.get(accountId);
		return "Balance is " + a1.getBalance();

	}
	
	//this method use for deposit amount. 
	@Override
	public String Deposit(String accountId, int amount) {

		a1 = UserAccountData.get(accountId);
		a1.setBalance(a1.getBalance() + amount);
		String date = sd.format(new Date());
		String time = sd1.format(new Date().getTime());
		//t.makeTransaction("WithDraw", amount,accountId,"null");
		a1.setTransaction("Transaction type = Deposit || fromAccount = "+accountId
				+" || Amount = "+amount+"|| Date ="+date+"|| Time = "+time );
		return "After deopsit user balance is " + a1.getBalance();
	}
	
	//this method use for withdraw amount.
	@Override
	public String withDraw(String accountId, int amount) {
		a1 = UserAccountData.get(accountId);
		a1.setBalance(a1.getBalance() - amount);
		String date = sd.format(new Date());
		String time = sd1.format(new Date().getTime());
		//t.makeTransaction("WithDraw", amount,accountId,"null");
		a1.setTransaction("Transaction type = WithDraw || fromAccount = "+accountId
				+" || Amount = "+amount+"|| Date ="+date+"|| Time = "+time );
		return "After withdraw user balance is " + a1.getBalance();
	}
	
	//this method use to sign in into account.
	//return 1 if valid ,else return 0.
	@Override
	public int SignIn(String accountId, String accountPassword) {
		if (UserAccountData.containsKey(accountId)) {
			a1 = UserAccountData.get(accountId);
			if (a1.getAccountPassword().equals(accountPassword)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
		
	}

	//this method use for fund transfer data.
	//sourceAccountId = user one who want to transfer amount.
	//destrinationAccountId = user whom to transfer amount.
	@Override
	public String fundTransfer(String sourceAccountId, String destinationAccountId, int amount) {
			a1 = (UserBean) UserAccountData.get(sourceAccountId);
			if (a1.getBalance() >= amount) {
				String date = sd.format(new Date());
				String time = sd1.format(new Date().getTime());
				UserBean a2;
				a2 = UserAccountData.get(destinationAccountId);
				a1.setBalance(a1.getBalance() - amount);
				a2.setBalance(a2.getBalance() + amount);
				a1.setTransaction("Transaction type = Fund Transfer || fromAccount = "+sourceAccountId
						+" || toAccount ="+destinationAccountId+" || Amount = "+amount+"|| Date ="+date+"|| Time = "+time );
				a2.setTransaction("Transaction type = Fund Transfer || fromAccount = "+sourceAccountId
						+" || toAccount ="+destinationAccountId+" || Amount = "+amount+"|| Date ="+date+"|| Time = "+time );
				return "After transfer fund your balance is " + a1.getBalance();
			} else {
				return "You have insufficient amount to transfer";
			}
		
	}
	
	//this method print the all transaction of logged in user.
	@Override
	public String printTransactions(String accountId) {
		a1 = UserAccountData.get(accountId);
		//System.out.println(a1.getTransaction());
		
		String s = "";
		Iterator i = a1.getTransaction().iterator();
		while(i.hasNext()) {
			s = s+ "\n"+ (String) i.next();
		}
		return s;
	}

	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////validation/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	

//	@Override
//	public boolean accountIdCheck(String accountId) {
//		System.out.println(accountId);
//		System.out.println(UserAccountData.size());
//		if (UserAccountData.containsKey(accountId)) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	
	//this method check account id is in collection or not.
	@Override
	public boolean validAccountId(String accountId) {
		if (UserAccountData.containsKey(accountId)) {
			return true;
		} else {
			return false;
		}
	}
	
	//this method check user have insufficient balance or not.
	@Override
	public boolean checkBalance(String accountId, int amount) {
		a1 = (UserBean) UserAccountData.get(accountId);
		if(a1.getBalance() >= amount) {
			return true;
		}
		else {
			return false;
		}
	}


}
