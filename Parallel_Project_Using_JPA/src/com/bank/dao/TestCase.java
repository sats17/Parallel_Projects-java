package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.user.bean.UserBean;

class TestCase {

//	int accountNumber ;
//	@Test
//	void testAccountCreate() {
//		DaoClass db = new DaoClass();
//		UserBean ub = new UserBean("satish", "Password123", 86930, 500);
//		int i = db.userAccountCreate(ub);
//		accountNumber = ub.getAccountId();
//		assertEquals(1, i);
//	}
	
	@Test
	void testDeposit() {
		DaoClass db = new DaoClass();
		//add data like this.
		//withDraw(int accountId, int amount)
		String s = db.Deposit(1,100); 
		assertEquals("Deposit Successfull", s);
	}
	
	
	@Test
	void testWithdraw() {
		DaoClass db = new DaoClass();
		//add data like this.
		//withDraw(int accountId, int amount)
		String s = db.withDraw(1, 50); 
		assertEquals("Withdraw Successfull", s);
	}
	
	@Test
	void testFundtransfer() {
		DaoClass db = new DaoClass();
		//add data like this.
		//fundTransfer(int sourceAccountId, int destinationAccountId, int amount)
		String s = db.fundTransfer(1, 2, 20); 
		assertEquals("Transfer Successfull", s);
	}
	
	@Test
	void testSignIn() {
		DaoClass db = new DaoClass();
		//add data like this.
		//fundTransfer(int sourceAccountId, int destinationAccountId, int amount)
		int s = db.SignIn(1, "Password123"); 
		assertEquals(1, s);
	}
	
	
	
	
}
