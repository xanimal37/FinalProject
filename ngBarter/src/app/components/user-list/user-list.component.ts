import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users:User[] = [];
showList: boolean = false;
loggedInUser: User| null = null;

  constructor(
    private userService:UserService,
    private authService:AuthService
  ){

  }
  ngOnInit() {
    this.authService.getLoggedInUser().subscribe(
      (user: User) => {
        this.loggedInUser = user;
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.loadUsers();
  }
  loadUsers(){
    this.userService.getUsers().subscribe({
      next: (users:User[]) => {
        this.users = users;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }
    });

  }

}
