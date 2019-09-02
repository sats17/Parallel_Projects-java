package com.bankbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bankbackend.entities.TransactionBean;
import com.bankbackend.entities.UserBean;
import com.bankbackend.exceptions.GlobalException;



public interface BankServiceInterface {
	
	//This all service methods
	UserBean userAccountCreate(UserBean ub);
	
	UserBean signIn(int accId,String accPassword) ;
	
	int showBalance(int accId);
	
	int depositAmount(UserBean ub);
	
	int withdrawAmount(UserBean ub);
	
	int fundTransfer(int accountId,UserBean ub);
	
	Set<TransactionBean> transactionList(int accountId) ;
	
	UserBean isValidId(int id);

	
	
	
}