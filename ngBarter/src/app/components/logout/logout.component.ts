import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {
constructor(
  private router: Router,
  private auth:AuthService,

  ){

}

  logout(){
    this.auth.logout();

    console.log("you are logged out");
    this.router.navigateByUrl('/home')

  }



}
