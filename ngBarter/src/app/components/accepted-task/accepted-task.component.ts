import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AcceptedTask } from 'src/app/models/accepted-task';
import { User } from 'src/app/models/user';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-accepted-task',
  templateUrl: './accepted-task.component.html',
  styleUrls: ['./accepted-task.component.css']
})
export class AcceptedTaskComponent implements OnInit {

  acceptedTasks: AcceptedTask[]=[];
  @Input() loggedInUser: User | undefined;

  constructor(
    private acceptedTaskService: AcceptedTaskService,
    private authService: AuthService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router){}

    ngOnInit(): void {
      this.loadAcceptedTasks();
    }

    loadAcceptedTasks():void {
      console.log("loading accepted tasks");
      this.acceptedTaskService.index().subscribe(
          {
            next: (accTasks) => {
              this.acceptedTasks = accTasks;
            },
            error: (problem) => {
              console.error('AcceptedTaskHttpComponent.loadTasks(): error retreiving accepted tasks:');
              console.error(problem);
            }
          }
        );
      }
}
