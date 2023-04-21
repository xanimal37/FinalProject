import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private auth:AuthService , private router:Router){

  }
  newUser: User = new User();



register(user: User): void {
  console.log('Registering user:');

  this.auth.register(user).subscribe({
    next: (registeredUser) => {
      this.auth.login(user.username, user.password).subscribe({
        next: (loggedInUser) => {
          console.log(loggedInUser);

          // this.router.navigateByUrl('/todo');
        },
        error: (problem) => {
          console.error('RegisterComponent.register(): Error logging in user:');
          console.error(problem);
        }
      });
    },
    error: (fail) => {
      console.error('RegisterComponent.register(): Error registering account');
      console.error(fail);
    }
  });
}
}


