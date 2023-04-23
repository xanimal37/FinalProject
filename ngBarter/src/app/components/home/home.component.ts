import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userCount!: number;
  loggedInUser: User = new User();

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.getCountOfUser();
  }

  getCountOfUser():void {
    this.userService.getUserCount().subscribe({
      next:(userCount:number)=>{
        this.userCount = userCount;
      },
      error: (nojoy: any) => {
        console.log(nojoy);
      }
    })
}


}
