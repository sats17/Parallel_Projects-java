package com.bankbackend.controller;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bankbackend.entities.TransactionBean;
import com.bankbackend.entities.UserBean;
import com.bankbackend.exceptions.GlobalException;
import com.bankbackend.service.BankServiceClass;
import com.bankbackend.util.ExceptionResponse;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RestApiController {

	@Autowired
	private BankServiceClass ser;
	
	 @PostMapping(value = "/create")
	 public UserBean addUserData(@RequestBody final UserBean usr) {
		 return ser.userAccountCreate(usr);
	 }
	 
	 @PutMapping(value = "/deposit")
	 public int depositAmount(@RequestBody final UserBean usr)  {
		 
		 return ser.depositAmount(usr);
	 }
	 
	 @PutMapping(value = "/withdraw")
	 public int withdrawAmount(@RequestBody final UserBean usr)  {
		 if(usr.getBalance() <= 0) {
				throw new GlobalException("Withdraw amount must be greater than 0");
		 }
		 return ser.withdrawAmount(usr);
	 }
	 
	 @GetMapping(value = "/balance/{id}")
	 public int showBalance(@PathVariable final int id)  {
		 
		 return ser.showBalance(id);
	 } 	
	 
	 @PutMapping(value = "/fundtransfer/{accountId}")
	 public int fundTransfer(@PathVariable final int accountId,@RequestBody final UserBean usr)  {
		 if(usr.getAccountId() == accountId) {
			 throw new GlobalException("Cannot transfer to same account id");
		 }	 
		 return ser.fundTransfer(accountId, usr);
	 }
	 
	 @GetMapping(value = "/transactions/{id}")
	 public Set<TransactionBean> printTransaction(@PathVariable final int id)  {
		 return ser.transactionList(id);
	 }
	 
	 @PostMapping(value = "/signin")
	 public UserBean signIn(@RequestBody final UserBean usr)  {
		 int id = usr.getAccountId();
		 String password = usr.getAccountPassword();
		 return ser.signIn(id, password);
	 } 
	 
	 @GetMapping(value = "/check/{id}")
	 public UserBean isValidId(@PathVariable final int id) {
		 UserBean name = ser.isValidId(id);
		 return name;
	 }
	 

	 
	 
}
