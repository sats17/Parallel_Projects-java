import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { BankserviceService } from '../services/bankservice.service';
import { AuthServicesService } from '../services/auth-services.service';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  
  loginForm: FormGroup;
  loading = false;
  submitted = false;



  constructor(  private formBuilder: FormBuilder,
                private router: Router,
                private alertService: AlertService,
                private bankService: BankserviceService,
                private auth: AuthServicesService
                ) { 
    this.formBuilder = formBuilder;
    this.router = router;
}

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      accountid: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    
  });
  }

  get f() { return this.loginForm.controls; }

  onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.auth.sendToken(this.loginForm.value.accountid)
    var accountid = this.loginForm.get("accountid").value;
    var password = this.loginForm.get("password").value;

    if(this.bankService.LoginUser(accountid,password)){
      this.alertService.success('Login successful', true);
    this.router.navigate(['/useractions']);
    }
    else{
      this.alertService.success('Login Failed', true);
      this.router.navigate(['/signin']);
    }
    
   
  }

}
