package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bank.user.bean.UserBean;

class UnitTesting {
	DaoClass ds = new DaoClass();
	int accountId=123;
	UserBean ub = new UserBean();
	
	@Test
	void createAccountTest() {
		ub.setAccountId("12");
		ub.setAccountPassword("password123");
		
		int a=ds.userAccountCreate(ub.getAccountId(), ub) ;
		assertEquals(1, a);
	}
	
	@Test
	void signInTest() {
		int a=ds.SignIn("123","password") ;
		assertEquals(0, a);
	}
	
	@Test
	void depositTest() {
		String s = ds.Deposit("SA098413", 500);
		assertEquals("Deposit successfull",s);
	}
	
	@Test
	void withdrawTest() {
		String s = ds.withDraw("SA098413", 500);
		assertEquals("Deposit successfull",s);
	}
	
}
