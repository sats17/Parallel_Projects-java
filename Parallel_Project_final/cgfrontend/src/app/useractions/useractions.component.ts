import { Component, OnInit } from '@angular/core';
import { AuthServicesService } from '../services/auth-services.service';
import { BankserviceService } from '../services/bankservice.service';
import { AlertService } from '../services/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-useractions',
  templateUrl: './useractions.component.html',
  styleUrls: ['./useractions.component.css']
})
export class UseractionsComponent implements OnInit {

  constructor(private auth:AuthServicesService,private bankService:BankserviceService,
      private alertService:AlertService,private router:Router) { }

  ngOnInit() {
    if(this.auth.isLoggedIn()){
      var id = this.auth.getToken();
      var returnObj = this.bankService.IsValidAccountId(Number(id));
      returnObj
              .subscribe((data) =>{
               //  this.alertService.success("Welcome "+data['name'], true);
                this.router.navigate(['/useractions']);
              },
              (error)=>{
                this.alertService.error("You are not logged in.", true);
                this.router.navigate(['/signin']);
              })
 
      }
  }

}
