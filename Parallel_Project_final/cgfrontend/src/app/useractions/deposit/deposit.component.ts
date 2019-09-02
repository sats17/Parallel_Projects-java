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
        Validators.pattern('^[1-9][0-9]{0,11}$')  // validates input is digit
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
    var depositObj = this.bankService.DepositAmount(amount);

    depositObj
             .subscribe((data) => {
              this.alertService.success('Deposit successful. Your Current Balance is '+data, true);
              this.router.navigate(['/useractions']);
             },
             (error) => {
              if(error['error']['httpCodeMessage'] === 'Forbidden'){
                this.alertService.error(error['error']['message'], true);
                this.router.navigate(['/signin']);
              }
              else{
              this.alertService.error(error['error']['message'], true);
              this.router.navigate(['/useractions/deposit']);
              }
             })

    
   
   
  }


}
