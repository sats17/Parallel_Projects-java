import { Component, OnInit } from '@angular/core';
import { BankserviceService } from 'src/app/services/bankservice.service';

@Component({
  selector: 'app-showbalance',
  templateUrl: './showbalance.component.html',
  styleUrls: ['./showbalance.component.css']
})
export class ShowbalanceComponent implements OnInit {

  constructor(
    private bankService:BankserviceService
  ) { }
  displayBalance:number;
  ngOnInit() {
    var balance = this.bankService.ShowBalance();
    if(balance != -1){
      this.displayBalance = balance;
    }
    else{
      this.displayBalance = 0;
    }
  }

}
