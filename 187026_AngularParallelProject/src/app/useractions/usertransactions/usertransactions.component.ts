import { Component, OnInit } from '@angular/core';
import { BankserviceService } from 'src/app/services/bankservice.service';
import { UserTransactions } from 'src/app/models/userTransactions';

@Component({
  selector: 'app-usertransactions',
  templateUrl: './usertransactions.component.html',
  styleUrls: ['./usertransactions.component.css']
})
export class UsertransactionsComponent implements OnInit {

  transactions:UserTransactions[] = [];

  constructor(
    private bankService:BankserviceService
  ) { }

  ngOnInit() {

    this.transactions = this.bankService.PrintTransactions();
    console.log(this.transactions)

  }

}
