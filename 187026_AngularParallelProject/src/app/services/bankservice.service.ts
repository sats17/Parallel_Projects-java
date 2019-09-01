import { Injectable } from '@angular/core';
import { userModel } from '../models/userModel';
import { UserTransactions } from '../models/userTransactions';

@Injectable({
  providedIn: 'root'
})
export class BankserviceService {

  registerUsers:userModel[] = []
  usersTransactions:UserTransactions[] = []

  currentUserAccId:number;
  currentUserAccPass:string;

  constructor() { }


  accountIdGenerator():number{
    return Math.floor(Math.random() * 10000);
  }


  RegisterUser(username:string,password:string,mobilenumber:number):number{

    var accountid = this.accountIdGenerator();
    var userObj = new userModel(accountid,password,username,mobilenumber);
   
    var isPushed  = this.registerUsers.push(userObj); 
    if(isPushed){
      console.log("asdfasdf"+this.registerUsers)
      return accountid;
      
    }
    else{
      return -1;
    }
    
  }

  LoginUser(accountid:number,password:string):boolean{
    if(this.registerUsers.length != 0){
      for(let i = 0; i < this.registerUsers.length ; i++){
        
        if(accountid == this.registerUsers[i].accountId && password == this.registerUsers[i].accountPassword ){
          console.log("success")
          this.currentUserAccId = accountid;
          this.currentUserAccPass = password;
          return true;
        }
      }
      return false;
    }
    else{
      console.log("this error")
      return false;
    }
    
  }

  ShowBalance(){
    for(let i = 0; i < this.registerUsers.length ; i++){
        
      if(this.currentUserAccId == this.registerUsers[i].accountId && this.currentUserAccPass == this.registerUsers[i].accountPassword ){     
        return this.registerUsers[i].balance;
      }
    }
    return -1;
  }

  DepositAmount(amount:any):number{
    for(let i = 0; i < this.registerUsers.length ; i++){
        
      if(this.currentUserAccId == this.registerUsers[i].accountId && this.currentUserAccPass == this.registerUsers[i].accountPassword ){
        console.log("success")
        
        console.log(typeof(this.registerUsers[i].balance))
        console.log("amt "+typeof(amount))
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        this.registerUsers[i].balance = this.registerUsers[i].balance + parseInt(amount,10)
        var tObj = new UserTransactions(this.currentUserAccId,"Deposit",date,amount,null)
        this.usersTransactions.push(tObj)
        return this.registerUsers[i].balance;
      }
    }
    return -1;
  }

  WithdrawAmount(amount:any):number{
    for(let i = 0; i < this.registerUsers.length ; i++){
        
      if(this.currentUserAccId == this.registerUsers[i].accountId && this.currentUserAccPass == this.registerUsers[i].accountPassword ){
        
        if(this.registerUsers[i].balance < amount){
          return -1
        }

        this.registerUsers[i].balance = this.registerUsers[i].balance - parseInt(amount,10)
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        var tObj = new UserTransactions(this.currentUserAccId,"Withdraw",date,amount,null)
        this.usersTransactions.push(tObj)
        return this.registerUsers[i].balance;
      }
    }
    return -1;
  }

  FundTransfer(accountid:any,amount:any):boolean{
    var to:boolean = false;
    var from:boolean = false;
    for(let i = 0; i < this.registerUsers.length ; i++){
        
      if(this.currentUserAccId == this.registerUsers[i].accountId && this.currentUserAccPass == this.registerUsers[i].accountPassword ){
        
        if(this.registerUsers[i].balance < amount){
          return false
        }

        this.registerUsers[i].balance = this.registerUsers[i].balance - parseInt(amount,10)
        from = true
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        var tObj = new UserTransactions(this.currentUserAccId,"Fund Transfer To",date,amount,accountid)
        this.usersTransactions.push(tObj)
        break;
      }
    }

    for(let i = 0; i < this.registerUsers.length ; i++){
        
      if(accountid == this.registerUsers[i].accountId){
        
        this.registerUsers[i].balance = this.registerUsers[i].balance + parseInt(amount,10)
        to = true;
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        var tObj = new UserTransactions(accountid,"Fund Transfer From",date,amount,this.currentUserAccId)
        this.usersTransactions.push(tObj)
        break;
      }
    }
    if(to && from){
      return true
    }
    else{
      return false;
    }
  }


  PrintTransactions():UserTransactions[]{
    let trans:UserTransactions[] = [];
    for(let i = 0; i < this.usersTransactions.length ; i++){
      
      if(this.currentUserAccId == this.usersTransactions[i].accountId ){
        console.log(this.usersTransactions[i])
        trans.push(this.usersTransactions[i]);
      }
    }
    return trans;
  }



}
