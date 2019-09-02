import { Component, OnInit } from '@angular/core';
import { BankserviceService } from 'src/app/services/bankservice.service';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-showbalance',
  templateUrl: './showbalance.component.html',
  styleUrls: ['./showbalance.component.css']
})
export class ShowbalanceComponent implements OnInit {

  constructor(
    private bankService:BankserviceService,
    private router: Router,
    private alertService: AlertService
  ) { }
  displayBalance:number;
  ngOnInit() {
    var showBalObj = this.bankService.ShowBalance();

    showBalObj
             .subscribe((data) => {
               this.displayBalance = data;
             },
             (error)=>{
              this.alertService.error('You are not logged in', true);
              this.router.navigate(['/signin']);
             })
  }

}
