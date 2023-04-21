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
  constructor(
    private userService:UserService
  ){

  }
  ngOnInit() {
    this.getAllUsers();
  }
  getAllUsers(){
  this.userService.getUsers().subscribe({
   next:(users: User[]) => {
      this.users = users;
    },
    error:(nojoy) => {
      console.log(nojoy);
    }
    }
   );
  }

}
