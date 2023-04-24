import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  loggedInUser: User | undefined;
  admin: string = "admin";

  constructor(
    private auth: AuthService){

  }
  isCollapsed: boolean = false;

  ngOnInit(): void {
    this.verifyUser();
   }

  loggedIn():boolean{
    return this.auth.checkLogin();
  }

  verifyUser(): void{
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });
    }
}
