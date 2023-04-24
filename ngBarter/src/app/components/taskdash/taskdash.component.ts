import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';
import { TaskStatusService } from 'src/app/services/task-status.service';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-taskdash',
  templateUrl: './taskdash.component.html',
  styleUrls: ['./taskdash.component.css']
})
export class TaskdashComponent implements OnInit {

  loggedInUser : User | null = null;
  dashboard : string = 'all';

  constructor(
    private taskService:TaskService,
    private skillService:SkillService,
    private taskStatusService: TaskStatusService,
    private acceptedTaskService: AcceptedTaskService,
    private authService: AuthService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    this.checkUserLoggedIn();
  }

  checkUserLoggedIn(){
    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });

  }

  selectView(view: string){
    this.dashboard=view;
  }

}
