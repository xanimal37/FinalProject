import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
export class TaskdashComponent {

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

}
