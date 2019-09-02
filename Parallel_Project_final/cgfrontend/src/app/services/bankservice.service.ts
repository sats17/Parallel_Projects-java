import { Injectable } from '@angular/core';
import { userModel } from '../models/userModel';
import { UserTransactions } from '../models/userTransactions';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthServicesService } from './auth-services.service';

@Injectable({
  providedIn: 'root'
})
export class BankserviceService {

  http:HttpClient 
  authService:AuthServicesService
  registerUsers:userModel[] = []
  usersTransactions:UserTransactions[] = []

  

  constructor(http:HttpClient,authService:AuthServicesService) {
    this.authService = authService;
    this.http = http;

   }
   


  RegisterUser(username:string,password:string,mobilenumber:string):Observable<any>{
    let jsonObj = {
      "name":username,
      "accountPassword":password,
      "mobileNumber":mobilenumber
    }
    let url = 'http://localhost:4444/api/create'

    return this.http.post(url,jsonObj);
    
  }

  LoginUser(accountid:number,password:string):Observable<any>{
    let url = 'http://localhost:4444/api/signin';
    let jsonObj = {
      "accountId":accountid,
      "accountPassword":password
    }
    return this.http.post(url,jsonObj);
  }

  ShowBalance():Observable<any>{
    let accId = parseInt(this.authService.getToken());
    let url = "http://localhost:4444/api/balance/"+accId;
    return this.http.get(url);
   
  }

  DepositAmount(amount:any):Observable<any>{

    let url = "http://localhost:4444/api/deposit";
    let accId = parseInt(this.authService.getToken());
    let jsonObj = {
      "accountId":accId,
      "balance":amount
    }
    return this.http.put(url,jsonObj);

  }

  WithdrawAmount(amount:any):Observable<any>{

    let url = "http://localhost:4444/api/withdraw";
    let accId = parseInt(this.authService.getToken());
    let jsonObj = {
      "accountId":accId,
      "balance":amount
    }
    return this.http.put(url,jsonObj);
  }

  FundTransfer(accountid:any,amount:any):Observable<any>{
   
    let url = 'http://localhost:4444/api/fundtransfer/'+accountid;
    let accId = parseInt(this.authService.getToken())

    let jsonObj = {
      "accountId":accId,
      "balance":amount
    }

    return this.http.put(url,jsonObj);
    
  }



  PrintTransactions():Observable<any>{
    
    let accId = parseInt(this.authService.getToken())
    let url = 'http://localhost:4444/api/transactions/'+accId;

    return this.http.get(url);
    
  }

  IsValidAccountId(id:number):Observable<any>{
    let url = 'http://localhost:4444/api/check/'+id;
    return this.http.get(url);
  }



}
