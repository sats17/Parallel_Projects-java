package com.bank.dao;

import org.junit.jupiter.api.Test;

public class TestCase extends junit.framework.TestCase {

	
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
