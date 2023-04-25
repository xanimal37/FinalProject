import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';

@Component({
  selector: 'app-other-user-profile',
  templateUrl: './other-user-profile.component.html',
  styleUrls: ['./other-user-profile.component.css']
})
export class OtherUserProfileComponent implements OnInit {

  user: User = new User();
  loggedInUser: User = new User();
  words:string[]=[];
  currentUser: any;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService :AuthService,
    private router: Router,
    private acceptedTaskService: AcceptedTaskService,

    ) { }


ngOnInit() {
   this.userService.refreshUsers.subscribe(id => this.reloadEmit(id));


  this.reload();
}

reloadEmit(id:number) {
  this.userService.getUserById(id).subscribe({
    next: (user: User) => {
      this.user = user;
      this.getLoggedInUser();
      window.location.reload();

    },
    error: (nojoy) => {
      console.log(nojoy);
    }
  });

}
reload() {
  const userId = this.route.snapshot.params['id'];
  this.userService.getUserById(userId).subscribe({
    next: (user: User) => {
      this.user = user;
      this.getLoggedInUser();

    },
    error: (nojoy) => {
      console.log(nojoy);
    }
  });

}

getLoggedInUser(){
  this.authService.getLoggedInUser().subscribe({
    next:(user:User)=>{
      this.loggedInUser = user;
    },
    error: (nojoy) => {
      console.log(nojoy);
    }
  })
}


goBack() {
  this.router.navigate(['/usersList']);
}
addFriend() {

this.userService.addFriend( this.user.id,this.user).subscribe({

    next: (response) => {
        console.log(response);
        this.reload();

      },
      error: (nojoy) => {
        console.log(nojoy);

        this.reload();
        }

 });



      // this.userService.updateUser(this.loggedInUser).subscribe({
      //   next:(joy)=>{
      //       console.log(joy);

      //     },
      //     error:(nojoy)=>{
      //         console.log(nojoy);

      //       }

      //     })



}
}

