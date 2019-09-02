import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ReactiveFormsModule }    from '@angular/forms';
import { UseractionsComponent } from './useractions/useractions.component';
import { DepositComponent } from './useractions/deposit/deposit.component';
import { WithdrawComponent } from './useractions/withdraw/withdraw.component';
import { FundtransferComponent } from './useractions/fundtransfer/fundtransfer.component';
import { UsertransactionsComponent } from './useractions/usertransactions/usertransactions.component';
import { AlertComponent } from './alert/alert.component';
import { ShowbalanceComponent } from './useractions/showbalance/showbalance.component';
import { LogoutComponent } from './useractions/logout/logout.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SignInComponent,
    UseractionsComponent,
    DepositComponent,
    WithdrawComponent,
    FundtransferComponent,
    UsertransactionsComponent,
    AlertComponent,
    ShowbalanceComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
