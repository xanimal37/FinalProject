import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { UserListComponent } from '../user-list/user-list.component';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{

  loggedInUser: User | undefined;
  admin: string = "admin";

  constructor(
    private auth: AuthService,
    private userService: UserService,
    private userList: UserListComponent
    ){

    }

  ngOnInit(): void {
    this.verifyUser();
    this.auth.getLoggedIn.subscribe(user => this.loggedInUser = user)
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
