package com.bank.mainui;

import java.util.Scanner;

import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientFundException;
import com.bank.service.BankServiceClass;
import com.bank.service.BankServiceInterface;



public class MainUi {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//User information variables.
		String userName;
		String accountPassword;
		String mobileNumber;
		String amount;
		
		BankServiceInterface serviceObject = new BankServiceClass();//created object of service class.
		
		
		
		boolean exitFromApp = false;
		//this loop will run till exitFromApp is false.
		while(!exitFromApp) {
			
			System.out.println("*************Welcome to dashboard*************");
			System.out.println("Enter 1 for Sign Up\nEnter 2 for Sign In\nEnter 3 for Exit");
			
			String dashboardUserInput = sc.nextLine();
			//sc.nextLine();
			
			switch(dashboardUserInput) {
			
			 	case "1":
			 		
			 		 System.out.println("Enter Your name");
			 		 userName = sc.nextLine();
			 		 String updateName = serviceObject.nameCheck(userName);  //validation method for name.
			 		 if(updateName.equals("exit")) {   						// if service class return exit switch case will break.
			 			 break;
			 		 }
			 		 
			 		 System.out.println("Enter account password");
			 		 accountPassword = sc.nextLine();
				 	 String updatePassword = serviceObject.passwordCheck(accountPassword);//validation method for password.
				 	 if(updatePassword.equals("exit")) { 	// if service class return exit switch case will break.
			 			 break;
			 		 }
				 	 
				 	 System.out.println("Enter mobile number");
				 	 mobileNumber = sc.nextLine();
				 	 String updateMobileNumber = serviceObject.mobileNumberCheck(mobileNumber);//validation method for mobile number.
				 	
				 	 if(updateMobileNumber.equals("exit")) { // if service class return exit switch case will break.
			 			 System.out.println("this");
				 		 break;
			 		 }
				 	 
				 	 //following statement create user account and return .
				 	 System.out.println(serviceObject.userAccountCreate(updatePassword,updateName,Long.parseLong(updateMobileNumber)));
				 	 break;
				 	 

			 	 case "2":
			 		 
			 		 //get user account id and check it present in collection.
			 		 System.out.println("Enter account ID");
			 		 String loggedInAccountId = sc.nextLine();
			 		 while(true) {
			 			 //this try-catch block throws user defined exception[AccountNotFoundException]
			 			 try {
			 				 loggedInAccountId = serviceObject.validAccountId(loggedInAccountId);
			 				 break;
			 			 }
			 			 catch(AccountNotFoundException e) {
			 				System.out.println(e);
			 				System.out.println("Enter again:[Enter exit for dashboard]");
			 				loggedInAccountId = sc.nextLine();
							if(loggedInAccountId.equals("exit")) {
								loggedInAccountId = "exit";
								break;
							}
			 			 }
			 		 }
			 		 if(loggedInAccountId.equals("exit")) { //if user enter exit switch case will be close.
			 			 break;
			 		 }
			 		 
			 		 //get password from user.
			 		 System.out.println("Enter account password");
			 		 String loggedInAccountPassword = sc.nextLine();
			 		 
			 		 //this will check user is genuinie or not. if i = 1 then user is genuinie or else not. 
			 		 int i = serviceObject.SignIn(loggedInAccountId, loggedInAccountPassword);
			 		 boolean returnToDashboard = false; 
			 		 
			 		 if(i == 1) {
			 			System.out.println("Sign in successfull");
			 			while(!returnToDashboard) {
			 				
			 				System.out.println("*************Welcome*************");
			 				System.out.println("Enter 1 for Show Balance\nEnter 2 for Deposit");
			 				System.out.println("Enter 3 for withdraw\nEnter 4 for fund transfer\nEnter 5 for print Transaction");
			 				System.out.println("Enter 6 for exit");
			 				
			 				String signInInput = sc.nextLine();
			 				
			 				
			 				switch(signInInput) {
			 				
			 				 	 case "1":
			 				 		 //this method show balance and print.
			 				 		 System.out.println(serviceObject.showBalance(loggedInAccountId));
			 				 		 break;
			 				 		 
			 				 	 case "2":
			 				 		 
			 				 		 System.out.println("Enter amount you want to deposit");
			 				 		 //this check amount limit.
			 				 		 amount = serviceObject.amountLimitCheck(sc.nextLine());
			 				 		 if(amount.equals("exit")) {
			 				 			 break;
			 				 		 }
			 				 		 
			 				 		 //this method use for deposit.
			 				 		 System.out.println(serviceObject.deposit(loggedInAccountId, Integer.parseInt(amount)));
			 				 		 break;
			 				 		 
			 				 	 case "3":
			 				 		 //this is withdraw case and its methods.
			 				 		 System.out.println("Enter amount you want to withdraw");
			 				 		 amount = sc.nextLine();
			 				 		 while(true) {
			 				 			 try {
			 				 				amount = serviceObject.checkBalance(loggedInAccountId,amount);
			 				 				break;
			 				 			 }
			 				 			 catch(InsufficientFundException e) {
			 				 				System.out.println(e);
			 				 				System.out.println("Enter again:[Enter exit for dashboard]");
			 				 				amount = sc.nextLine();
			 								if(amount.equals("exit")) {
			 									amount = "exit";
			 									break;
			 								}
			 				 			 } 
			 				 		 }
			 				 		 if(amount.equals("exit")) {
			 				 			 break;
			 				 		 }
			 				 		 System.out.println(serviceObject.withDraw(loggedInAccountId, Integer.parseInt(amount)));
			 				 		 break;
			 				 		 
			 				 	 case "4":
			 				 		 //this is fund transfer case and methods.
			 				 		 System.out.println("Enter amount you want gyito Transfer");
			 				 		 amount = sc.nextLine();
			 				 		 //following code check user have insufficient balance or not.
			 				 		 while(true) {
			 				 			 try {
			 				 				amount = serviceObject.checkBalance(loggedInAccountId,amount);
			 				 				break;
			 				 			 }
			 				 			 catch(InsufficientFundException e) {
			 				 				System.out.println(e);
			 				 				System.out.println("Enter again:[Enter exit for dashboard]");
			 				 				amount = sc.nextLine();
			 								if(amount.equals("exit")) {
			 									amount = "exit";
			 									break;
			 								}
			 				 			 }		 
			 				 		 }
			 				 		 if(amount.equals("exit")) {
			 				 			 break;
			 				 		 }
			 				 		 
			 				 		 System.out.println("Enter account ID user that you want to transfer amount");
			 				 		 String accountId2 = sc.nextLine() ;
			 				 		 //following code will check account id is in collection or not.
//			 				 		 while(true) { 
//			 				 			 try {
//			 				 				 accountId2 = serviceObject.validAccountId(accountId2);
//			 				 				 break;
//			 				 			 }
//			 				 			 catch(AccountNotFoundException e) {
//			 					 				System.out.println(e);
//			 					 				System.out.println("Enter again:[Enter exit for dashboard]");
//			 					 				accountId2 = sc.nextLine();
//			 									if(accountId2.equals("exit")) {
//			 										accountId2 = "exit";
//			 										break;
//			 									}
//			 					 		 	}
//			 				 		 	}
//				 				 	 if(accountId2.equals("exit")) {
//				 				 			 break;	 
//			 				 		 }
			 				 		 //this will transfer fund.
			 				 		 System.out.println(serviceObject.fundTransfer(loggedInAccountId, accountId2,Integer.parseInt(amount)));
			 				 		 break;
			 				 		 
			 				 	 case "5":
			 				 		 //print transaction part done here
			 				 		 System.out.println(serviceObject.printTransactions(loggedInAccountId));
			 				 		 break;
			 				 		 
			 				 	 case "6":
			 				 		 //if user want to return to dashboard . 
			 				 		 returnToDashboard = true ;
			 				 		 break;
			 				 	 default:
			 				 		System.out.println("enter valid input");
			 				}//End of inner switch statement.
			 			}//End of inner while loop.
			 		 }
			 		 else {
			 			 System.out.println("Wrong Credentials");
			 		 }
			 		 break;
			 	 case "3":
			 		 exitFromApp = true;
			 		 break;
			 	default:
			 		System.out.println("enter valid input");
			}//End of outer switch statement.
		}//End of outer while loop.
		sc.close();
		System.out.println("Thank You for using application");
		System.exit(1);

	}

}
