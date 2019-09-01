import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { AlertService } from 'src/app/services/alert.service';
import { BankserviceService } from 'src/app/services/bankservice.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  depositForm : FormGroup 
  submitted:boolean = false;


  constructor( private formBuilder:FormBuilder,
               private router:Router,
               private alertService:AlertService,
               private bankService: BankserviceService
               ) { 
      this.formBuilder = formBuilder;;
  }

  ngOnInit() {

    this.depositForm = this.formBuilder.group({
      amount: ['',[
        Validators.required,
        Validators.maxLength(10),
        Validators.pattern('[0-9]+')  // validates input is digit
      ]]
  });
  }

  get f() { return this.depositForm.controls; }


  onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.depositForm.invalid) {
        return;
    }
    
    var amount = this.depositForm.get("amount").value;
    var isDeposited = this.bankService.DepositAmount(amount);
    if(isDeposited != -1){
      this.alertService.success('Deposit successful. Your Current Balance is '+isDeposited, true);
      this.router.navigate(['/useractions']);
    }
    else{
      this.alertService.success('Deposit Failed , Try again', true);
      this.router.navigate(['/useractions/deposit']);
    }
   
   
  }


}
