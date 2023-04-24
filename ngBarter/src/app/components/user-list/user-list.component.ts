import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {


users:User[] = [];
showList: boolean = false;
loggedInUser: User| null = null;
accountStatus: string = '';


  constructor(
    private router: Router,
    private userService:UserService,
    private authService:AuthService
  ){

  }
  ngOnInit() {

    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });

    this.loadUsers();
  }
  viewUserProfile(user:User) {
    this.router.navigate(['/user-profile', user.id]);

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

  setAccountStatus(){
    if (this.loggedInUser?.enabled === true) {
      this.accountStatus = 'enabled'
    } else {
      this.accountStatus = 'disabled'
    }
    this.loadUsers()
  }
}
