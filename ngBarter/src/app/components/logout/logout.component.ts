import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {
constructor(private auth:AuthService){

}

  logout(){
    this.auth.logout();
    // this.router.navigateByUrl('/home')

  }

}
