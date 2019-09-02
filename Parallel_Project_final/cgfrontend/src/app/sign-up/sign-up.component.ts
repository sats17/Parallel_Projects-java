import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup , Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { BankserviceService } from '../services/bankservice.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

 

  registerForm: FormGroup;
  loading = false;
  submitted = false;

   
    constructor(  private formBuilder: FormBuilder,
                  private router: Router,
                  private alertService: AlertService,
                  private bankService: BankserviceService ) { 
      this.formBuilder = formBuilder;
      this.router = router;
  }

  ngOnInit() {

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      number: ['',[
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10),
        Validators.pattern('[6-9][0-9]{9}')  // validates input is digit
      ]]
  });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }
    var uname = this.registerForm.get('username').value;
    var upass = this.registerForm.get('password').value;
    var unum = this.registerForm.get('number').value;
    var registerObj = this.bankService.RegisterUser(uname,upass,unum);
    var accId:number = 0;
  
    
    registerObj
              .subscribe((data) => {
                        accId = data['accountId']
                        this.router.navigate(['/signin']);
                        this.alertService.success('Registration successful with account id '+accId,true);
                       
                      },
                    (error) => {
                      this.alertService.error(error['error']['message'], true);
                      this.router.navigate(['/signup']);
                      
                    }); 
    
    
}

}
