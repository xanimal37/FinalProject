import { NavbarComponent } from './../navbar/navbar.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private auth:AuthService,
    private router: Router,


    ){}

  loginUser : User = new User;

  login(user:User){
console.log(user);

    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        console.log(loggedInUser);
        if(loggedInUser.role === 'admin') {
          this.router.navigateByUrl('/admin');
        } else {
          this.router.navigateByUrl('/userAccount');
        }
      },
      error: (problem) => {
        console.error('RegisterComponent.register(): Error logging in user:');
        console.error(problem);
      }
    });
   }

}
