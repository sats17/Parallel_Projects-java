package com.bankbackend.service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankbackend.dao.TransactionRepo;
import com.bankbackend.dao.UsersRepo;
import com.bankbackend.entities.TransactionBean;
import com.bankbackend.entities.UserBean;
import com.bankbackend.exceptions.GlobalException;
import com.bankbackend.exceptions.InvalidAuthentication;
import com.bankbackend.exceptions.UnauthorizedException;


@Service("BankService")
public class BankServiceClass implements BankServiceInterface {
	
	
	@Autowired
	private UsersRepo userDao;
	
	@Autowired
	private TransactionRepo transDao;
	
	public static int generateRandomId() {
		Random random = new Random();
		int randomInteger = random.nextInt() & Integer.MAX_VALUE;
		return randomInteger;
	}
	
	public static int decryAccId(int id) {
		return id + 500000;
	}
	
	public TransactionBean createTransactions(String transactionType,int amount) {
		
		TransactionBean tb = new TransactionBean();
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		tb.setAmount(amount);
		tb.setTransactionDate(ts);
		tb.setTransactionType(transactionType);
		
		return tb;
		
	}
	

	@Override
	public UserBean userAccountCreate(UserBean ub) {
		
		if(!ub.getMobileNumber().matches("[6-9][0-9]{9}")) {
			throw new GlobalException("Invalid Mobile Nunber"); 	
		}
		ub.setAccountId(generateRandomId());
		UserBean returnUb = userDao.save(ub);
		
		return returnUb;
	}

	@Override
	public int depositAmount(UserBean ub) {
		if(ub.getBalance() <= 0) {
			throw new GlobalException("Amount must be greater than 0");
		}
		if(ub.getBalance() > 10000000) {
			throw new GlobalException("Amount must be less than 1000000000");
		}
		
		if(userDao.existsById(decryAccId(ub.getAccountId()))) {
			
			Optional<UserBean> optionalUb = userDao.findById(decryAccId(ub.getAccountId()));
			UserBean dataBaseUb = optionalUb.get();
			int total = ub.getBalance() + dataBaseUb.getBalance();
			dataBaseUb.setBalance(total);
			
			TransactionBean tb = createTransactions("Deposit", ub.getBalance());
			
			dataBaseUb.addTransactions(tb);	
			
			transDao.save(tb);
			userDao.save(dataBaseUb);
		
			
			return total;
			
		}
		
		throw new UnauthorizedException("Please log in first.");
	}

	@Override
	public int withdrawAmount(UserBean ub) {
		if(userDao.existsById(decryAccId(ub.getAccountId()))) {
			
			Optional<UserBean> optionalUb = userDao.findById(decryAccId(ub.getAccountId()));
			UserBean dataBaseUb = optionalUb.get();
			
			if(ub.getBalance() > dataBaseUb.getBalance()) {
				throw new GlobalException("Insufficient Balance");
			}
			
			
			int total = dataBaseUb.getBalance() - ub.getBalance();
			dataBaseUb.setBalance(total);
			
			TransactionBean tb = createTransactions("Withdraw", ub.getBalance());
			
			dataBaseUb.addTransactions(tb);
			transDao.save(tb);
			userDao.save(dataBaseUb);
			
			
			return total;
			
		}
		
		throw new UnauthorizedException("Please log in first.");
		
	}

	@Override
	public int showBalance(int accId) {
		
		int userAccId = decryAccId(accId);
		
		if(userDao.existsById(userAccId)) {
			
			Optional<UserBean> optionalUb = userDao.findById(userAccId);
			UserBean dataBaseUb = optionalUb.get();
			return dataBaseUb.getBalance();
			
		}
		
		throw new UnauthorizedException("Please log in first.");
	}
	
	

	@Override
	public int fundTransfer(int accountId, UserBean ub) {
	int totalAmt = 0;
	if(userDao.existsById(decryAccId(ub.getAccountId()))) {
			
			Optional<UserBean> optionalUb = userDao.findById(decryAccId(ub.getAccountId()));
			UserBean dataBaseUb = optionalUb.get();
			
			if(ub.getBalance() > dataBaseUb.getBalance()) {
				throw new GlobalException("Insufficient Balance");
			}
			
			int total = dataBaseUb.getBalance() - ub.getBalance();
			dataBaseUb.setBalance(total);
			
			totalAmt = total;
		
			
			TransactionBean tb = createTransactions("Fund Transfer To", ub.getBalance());			
			tb.setToAccountId(accountId);
			
			dataBaseUb.addTransactions(tb);
			transDao.save(tb);
			userDao.save(dataBaseUb);
			
	}
	else {
		throw new UnauthorizedException("Please log in first");
	}
	if(userDao.existsById(accountId)) {
		
		Optional<UserBean> optionalUb = userDao.findById(accountId);
		UserBean dataBaseUb = optionalUb.get();
		int total = ub.getBalance() + dataBaseUb.getBalance();
		dataBaseUb.setBalance(total);
		userDao.save(dataBaseUb);
		
		TransactionBean tb = createTransactions("Fund Transfer From", ub.getBalance());	
		tb.setToAccountId(decryAccId(ub.getAccountId()));
		
		dataBaseUb.addTransactions(tb);
		transDao.save(tb);
		userDao.save(dataBaseUb);
		return totalAmt;
		
	}
	else {
		throw new GlobalException("Account id not found");
	}
	
	
	}
	
	
	

	@Override
	public Set<TransactionBean> transactionList(int accountId)  {
		
		if(userDao.existsById(decryAccId(accountId))) {
			Optional<UserBean> optionalUb = userDao.findById(decryAccId(accountId));
			UserBean dataBaseUb = optionalUb.get();
			return dataBaseUb.getTransactions();
		}
		throw new UnauthorizedException("Please log in first");
	}

	@Override
	public UserBean signIn(int accId, String accPassword)  {

		if(userDao.existsById(accId)) {
			Optional<UserBean> optionalUb = userDao.findById(accId);
			UserBean dataBaseUb = optionalUb.get();
			if(dataBaseUb.getAccountPassword().equals(accPassword)) {
				return dataBaseUb;
			}
			else {
				throw new InvalidAuthentication("Password Not Matched");
			}
		}
		
		throw new InvalidAuthentication("Account not found.");
	}

	@Override
	public UserBean isValidId(int id) {	
		if(userDao.existsById(decryAccId(id))) {
			Optional<UserBean> optionalUb = userDao.findById(decryAccId(id));
			UserBean dataBaseUb = optionalUb.get();
			return dataBaseUb;
		}
		throw new UnauthorizedException("You are not logged in.");
	}
	


}
