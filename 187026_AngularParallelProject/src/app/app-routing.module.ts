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


const routes: Routes = [
 // { path: '**', redirectTo: '' },
  // { path:'', component:SignInComponent}
  { path: '', redirectTo: 'signin', pathMatch: 'full' },
  { path:"signin" , component:SignInComponent },
  { path:"signup" , component:SignUpComponent },
  { path:"exit"   , component:ExitComponent   },
  { path:"useractions" , component:UseractionsComponent },
  { path:"useractions/showbalance" , component:ShowbalanceComponent },
  { path:"useractions/deposit" , component:DepositComponent },
  { path:"useractions/withdraw" , component:WithdrawComponent },
  { path:"useractions/fundtransfer" , component:FundtransferComponent },
  { path:"useractions/usertransactions" , component:UsertransactionsComponent },
  { path: "useractions/logout", redirectTo: 'signin', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
