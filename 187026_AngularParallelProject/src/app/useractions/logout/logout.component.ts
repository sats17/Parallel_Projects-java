import { Component, OnInit } from '@angular/core';
import { AuthServicesService } from 'src/app/services/auth-services.service';
import { Router } from '@angular/router';

@Component({
  selector: 'logout',
  templateUrl: './logout.component.html'
})
export class LogoutComponent implements OnInit {

  constructor(
    private auth: AuthServicesService,
    private router : Router
  ) { }

  ngOnInit() {
    this.auth.logout();
    this.router.navigate(['/signin']);
  }
}
