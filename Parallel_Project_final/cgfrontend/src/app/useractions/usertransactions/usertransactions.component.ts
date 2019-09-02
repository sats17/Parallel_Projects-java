import { Component, OnInit } from '@angular/core';
import { BankserviceService } from 'src/app/services/bankservice.service';
import { UserTransactions } from 'src/app/models/userTransactions';
import { AlertService } from 'src/app/services/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usertransactions',
  templateUrl: './usertransactions.component.html',
  styleUrls: ['./usertransactions.component.css']
})
export class UsertransactionsComponent implements OnInit {

  transactions:UserTransactions[] = [];

  constructor(
    private bankService:BankserviceService,private alertService:AlertService,private router: Router
  ) { }

  ngOnInit() {

    var transactionsObj = this.bankService.PrintTransactions();
    transactionsObj
                  .subscribe((data) => {
                    data.sort(function(a,b){
                      return a.transactionId - b.transactionId
                    })
                    this.transactions = data;
                    
                  },
                  (error) =>{
                    this.alertService.error(error['error']['message'], true);
                    this.router.navigate(['/signin']);
                  }
                  )
  }

}
