package com.bank.service;

import com.bank.dao.DaoClass;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientFundException;
import com.bank.user.bean.TransactionBean;
import com.bank.user.bean.UserBean;
import java.util.*;



public class BankServiceClass implements BankServiceInterface {
	
	
	DaoClass ds = new DaoClass();
	Scanner sc = new Scanner(System.in);
	
	
	
	@Override
	public String userAccountCreate(String accountPassword, String userName, long mobileNumber) {
		// TODO Auto-generated method stub
		UserBean userbean = new UserBean();
//		String accountId = RandomAccountIdGenerator.generateRandom(userName, mobileNumber);
		userbean.setName(userName);
	
		userbean.setAccountPassword(accountPassword);
		userbean.setMobileNumber(mobileNumber);
	
		int i = ds.userAccountCreate(userbean);
		if(i == 1) {
			return "Your Account is created with account ID "+userbean.getAccountId();
		}
		else {
			System.out.println("error from service");
			return "There problem in account creation";
		}
	}
	@Override
	public String showBalance(int accountId) {
		String s = ds.showBalance(accountId);
		return s;
	}
	@Override
	public String deposit(int accountId, int amount) {
		// TODO Auto-generated method stub
		String s = ds.Deposit(accountId, amount);
		return s;
	}
	@Override
	public String withDraw(int accountId, int amount) {
		String s = ds.withDraw(accountId, amount);
		return s;
	}

	@Override
	public String fundTransfer(int sourceAccountId, int destinationAccountId,int amount) {
		String s = ds.fundTransfer(sourceAccountId, destinationAccountId, amount);
		return s;
	}
	
	@Override
	public int SignIn(int accountId, String accountPassword) {
		int i = ds.SignIn(accountId, accountPassword);
		if(i == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	@Override
	public String printTransactions(int accountId) {
		// TODO Auto-generated method stub
		List<TransactionBean> hm = ds.printTransactions(accountId);

		Iterator i = hm.iterator();
		
		String s = "";
		while(i.hasNext()) {
			
			TransactionBean t = (TransactionBean) i.next();
			
			if(t.getToAccountId() != 0) {
				s = s+"\n" + "transaction ID = "+t.getTransactionId()+"|| Transaction Type = "+t.getTransactionType()+" || Date = "+t.getTransactionDate()+" || account id = "+t.getUserbean().getAccountId()+"|| amount = "+t.getAmount()
				+" || to account id= "+t.getToAccountId();
			}
			else {
				s = s+"\n" + "transaction ID = "+t.getTransactionId()+"|| Transaction Type = "+t.getTransactionType()+" || Date = "+t.getTransactionDate()+" || account id = "+t.getUserbean().getAccountId()+"|| amount = "+t.getAmount()
				;
			}
			
			
		
			
		}
		
		return s;
		
		//return ds.printTransactions(accountId);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////validation/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//this method validate string.
	public  String nameCheck(String name) {
	       
		while(true) {
			
			try {
				if(name.matches("[A-Z a-z]{3,10}")) {
					return name;
				}
				else {
					
					throw new Exception();
				}
			}
			catch(Exception ex) {
				System.out.println("------------Input Error----------------");
				System.out.println("Please enter username where length is greater than 2 and less than 10.");
				System.out.println("Enter again:[Enter exit for dashboard]");
				name = sc.nextLine();
				if(name.equals("exit")) {
					return "exit";
				}
			}
		}
	}
	
	
	//this method validate password.
	public  String passwordCheck(String password) {
		while(true) {
			try {
				if(password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}$")) {
					return password;
					
				}
				else {
					throw new Exception();
				}
			}
			catch(Exception ex){
				System.out.println("Please enter valid password(i.e-'Password123')");
				System.out.println("Enter again:[Enter exit for dashboard]");
				password = sc.nextLine();
				if(password.equals("exit")) {
					return "exit";
				}
			}
			
		}
	}
	
	//this method validate user mobile number.
	public String mobileNumberCheck(String mobileNumber) {
		while(true) {
			try {
				if(mobileNumber.matches("[6-9][0-9]{9}")) {
					return mobileNumber;
					
				}
				else {
					throw new Exception();
				}
			}
			catch(Exception ex){
				System.out.println("Enter valid mobile number(i.e - 10 digit only)");
				System.out.println("Enter again");
				mobileNumber = sc.nextLine();
				if(mobileNumber.equals("exit")) {
					return "exit";
				}
			}
			
		}
	}
	
	
	//this method check user enter amount limit.(not greater than 999999).
	public String amountLimitCheck(String amount) {
		while(true) {
			try {
				if(amount.length() > 6 || amount.equals(0)) {
					throw new Exception();
				}
				else {
					return amount;
				}
			}
			catch(Exception ex){
				System.out.println("Enter valid amount.(less than 999999)");
				System.out.println("Enter again");
				amount = sc.nextLine();
				if(amount.equals("exit")) {
					return "exit";
				}
			}
		}
	}
	
	//this method check if account id present in collection or not.
	
	@Override
	public String validAccountId(String accountId) {
		char[] chars = accountId.toCharArray();
		StringBuilder sb = new StringBuilder();
	      for(char c : chars){
	         if(!Character.isDigit(c)){
	        	 throw new AccountNotFoundException("Invalid Account Number");
	         }
	      }
		if(ds.validAccountId(Integer.parseInt(accountId))) {
					return accountId;
				}
				else {
					throw new AccountNotFoundException("Invalid Account Number");
				}
			
	}
	
	//this method check balance in user account..
	@Override
	public String checkBalance(String accountId,String amount) {
		if(ds.checkBalance(Integer.parseInt(accountId), Integer.parseInt(amount))) { //Here we transfer amount to DAO class.
			return amount;
		}
		else {
			throw new InsufficientFundException("Insufficient amount");
		}

	}
	

}
