import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ExitComponent } from './exit/exit.component';
import { DepositComponent } from './useractions/deposit/deposit.component';
import { UseractionsComponent } from './useractions/useractions.component';
import { WithdrawComponent } from './useractions/withdraw/withdraw.component';
import { FundtransferComponent } from './useractions/fundtransfer/fundtransfer.component';
import { UsertransactionsComponent } from './useractions/usertransactions/usertransactions.component';
import { ShowbalanceComponent } from './useractions/showbalance/showbalance.component';

import { GuardGuard } from './authguard/guard.guard';
import { LogoutComponent } from './useractions/logout/logout.component';


const routes: Routes = [
 // { path: '* ', redirectTo: '' },
  // { path:'', component:SignInComponent}
  { path: '', redirectTo: 'signin', pathMatch: 'full' },
  { path:"signin" , component:SignInComponent },
  { path:"signup" , component:SignUpComponent },
  { path:"exit"   , component:ExitComponent   },
  { path:"useractions" , component:UseractionsComponent ,canActivate: [GuardGuard]},
  { path:"useractions/showbalance" , component:ShowbalanceComponent ,canActivate: [GuardGuard]},
  { path:"useractions/deposit" , component:DepositComponent ,canActivate: [GuardGuard]},
  { path:"useractions/withdraw" , component:WithdrawComponent ,canActivate: [GuardGuard]},
  { path:"useractions/fundtransfer" , component:FundtransferComponent ,canActivate: [GuardGuard]},
  { path:"useractions/usertransactions" , component:UsertransactionsComponent ,canActivate: [GuardGuard]},
  { path: "useractions/logout", component:LogoutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
