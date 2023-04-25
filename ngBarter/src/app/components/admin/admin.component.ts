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

  skillsAvailable = {
	  animationEnabled: true,
	  theme: "dark2",
	  exportEnabled: true,
	  title: {
		text: "Skills Available"
	  },
	  subtitles: [{
		text: "Percentage of users with skill"
	  }],
	  data: [{
		type: "doughnut", //change type to column, line, area, doughnut, etc
		indexLabel: "{name}: {y}%",
		dataPoints: [
			{ name: "Carpentry", y: 9.1 },
			{ name: "Plumbing", y: 3.7 },
			{ name: "IT/Networking", y: 26.4 },
			{ name: "LandScaping", y: 19.6 },
			{ name: "Gardening", y: 17.3 },
			{ name: "Welding", y: 5.0 },
			{ name: "Packing/Moving", y: 18.9 }
		]
	  }]
	}

  numberOfTasks = {
	  animationEnabled: true,
	  theme: "dark2",
	  exportEnabled: true,
	  title: {
		text: "Tasks Completed"
	  },
	  subtitles: [{
		text: "Number of tasks completed by users this year"
	  }],
	  data: [{
		type: "line", //change type to column, line, area, doughnut, etc
		indexLabel: "{name}: {y}%",
		dataPoints: [
      { name: "May", y: 15 },
			{ name: "June", y: 16 },
			{ name: "July", y: 12 },
			{ name: "August", y: 13 },
			{ name: "September", y: 19 },
			{ name: "October", y: 14 },
			{ name: "November", y: 10 },
			{ name: "December", y: 4},
			{ name: "January", y: 4 },
			{ name: "February", y: 6 },
			{ name: "March", y: 26.7},
			{ name: "April", y: 19.10 }
		]
	  }]
	}

  test3 = {
	  animationEnabled: true,
	  theme: "dark2",
	  exportEnabled: true,
	  title: {
		text: "Skills Available"
	  },
	  subtitles: [{
		text: "Percentage of users with skill"
	  }],
	  data: [{
		type: "doughnut", //change type to column, line, area, doughnut, etc
		indexLabel: "{name}: {y}%",
		dataPoints: [
			{ name: "Carpentry", y: 9.1 },
			{ name: "Plumbing", y: 3.7 },
			{ name: "IT/Networking", y: 26.4 },
			{ name: "LandScaping", y: 19.6 },
			{ name: "Gardening", y: 17.3 },
			{ name: "Welding", y: 5.0 },
			{ name: "Packing/Moving", y: 18.9 }
		]
	  }]
	}

  test4 = {
	  animationEnabled: true,
	  theme: "dark2",
	  exportEnabled: true,
	  title: {
		text: "Tasks Completed"
	  },
	  subtitles: [{
		text: "Number of tasks completed by users this year"
	  }],
	  data: [{
		type: "line", //change type to column, line, area, doughnut, etc
		indexLabel: "{name}: {y}%",
		dataPoints: [
      { name: "May", y: 15 },
			{ name: "June", y: 16 },
			{ name: "July", y: 12 },
			{ name: "August", y: 13 },
			{ name: "September", y: 19 },
			{ name: "October", y: 14 },
			{ name: "November", y: 10 },
			{ name: "December", y: 4},
			{ name: "January", y: 4 },
			{ name: "February", y: 6 },
			{ name: "March", y: 26.7},
			{ name: "April", y: 19.10 }
		]
	  }]
	}

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
