import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';
import { BankserviceService } from 'src/app/services/bankservice.service';

@Component({
  selector: 'app-fundtransfer',
  templateUrl: './fundtransfer.component.html',
  styleUrls: ['./fundtransfer.component.css']
})
export class FundtransferComponent implements OnInit {

  fundtransferForm : FormGroup
  submitted:boolean = false;

  constructor( private formBuilder:FormBuilder,
               private router:Router,
               private alertService:AlertService,
               private bankService: BankserviceService ) { 
      this.formBuilder = formBuilder;;
  }

  ngOnInit() {

    this.fundtransferForm = this.formBuilder.group({
      accountid: ['', Validators.required],
      amount: ['',[
        Validators.required,
        Validators.maxLength(10),
        Validators.pattern('[0-9]+')  // validates input is digit
      ]]
  });
  }

  get f() { return this.fundtransferForm.controls; }

  onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.fundtransferForm.invalid) {
        return;
    }

    var amount = this.fundtransferForm.get("amount").value;
    var accountid = this.fundtransferForm.get("accountid").value;
    
    if(this.bankService.FundTransfer(accountid,amount)){
      this.alertService.success('Transfer successful', true);
      this.router.navigate(['/useractions']);
    }
    else{
      this.alertService.success('Transfer Failed , Try again', true);
      this.router.navigate(['/useractions/fundtransfer']);
    }
    
 
  }
  

}
