import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServicesService } from '../services/auth-services.service';
import { AlertService } from '../services/alert.service';

@Injectable({
  providedIn: 'root'
})

export class GuardGuard implements CanActivate {
  constructor(private auth: AuthServicesService,private alertService: AlertService,
    private myRoute: Router){
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.auth.isLoggedIn()){
      return true;
    }else{
      //alert("you do not have permission here")
      this.myRoute.navigate(["/signin"]);
     // this.alertService.error('Please Login first', true);

      return false;
    }
  }
}
