import { AuthService } from './../../services/auth.service';
import { TaskStatus } from './../../models/task-status';
import { TaskService } from './../../services/task.service';
import { SkillService } from 'src/app/services/skill.service';
import { TaskStatusService } from 'src/app/services/task-status.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from 'src/app/models/task';
import { DatePipe } from '@angular/common';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { AcceptedTask } from 'src/app/models/accepted-task';
import { AcceptedTaskId } from 'src/app/models/accepted-task-id';
import { FormArray, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css']
})

export class TasklistComponent implements OnInit {

  title='Task List';
  tasks: Task[]=[];
  newTask: Task = new Task();
  selectedSkillName:string='all';
  selectedTask: Task | null = null;
  //properties of task
  skills: Skill[] =[];
  taskStatuses: TaskStatus[] =[];
  loggedInUser: User | undefined;
  //what the user is looking at
  //see TaskView class below
  views:TaskView[]=[];

  constructor(
  private taskService:TaskService,
  private skillService:SkillService,
  private taskStatusService: TaskStatusService,
  private acceptedTaskService: AcceptedTaskService,
  private authService: AuthService,
  private datePipe: DatePipe,
  private route: ActivatedRoute,
  private router: Router)
  {}

  ngOnInit(): void {
    this.initializeViews();
    this.checkUserLoggedIn();
    this.loadTasks();
    this.loadSkills();
    this.loadTaskStatuses();
  }

  setView(viewname:string){
    for(let view of this.views){
      view.visible=false;
      if(view.name == viewname){
        view.visible=true;
      }
    }
  }

  //set up the views and which are active
  //will toggle these
  initializeViews():void {
    this.views.push(new TaskView('default',true));
    this.views.push(new TaskView('adding',false));
    this.views.push(new TaskView('updating',false));
  }

  checkUserLoggedIn(){
    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
        console.log(user);
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });

  }

  loadTasks():void {
    this.taskService.index().subscribe(
        {
          next: (tasks) => {
            this.tasks = tasks;
            this.setView('default');
          },
          error: (problem) => {
            console.error('TaskListHttpComponent.loadTasks(): error retreiving tasks:');
            console.error(problem);
          }
        }
      );
    }

    loadSkills():void {
      this.skillService.index().subscribe(
          {
            next: (skills) => {
              this.skills = skills;
            },
            error: (problem) => {
              console.error('TaskListHttpComponent.loadSkills(): error retreiving skills:');
              console.error(problem);
            }
          }
        );
      }

      loadTaskStatuses():void {
        this.taskStatusService.index().subscribe(
            {
              next: (taskStatuses) => {
                this.taskStatuses = taskStatuses;
              },
              error: (problem) => {
                console.error('TaskListHttpComponent.loadTaskStatuses(): error retreiving task statuses:');
                console.error(problem);
              }
            }
          );
        }

  acceptTask(task: Task){
    let aTaskId = new AcceptedTaskId();
    if(this.loggedInUser && this.selectedTask){
      aTaskId.acceptorId=this.loggedInUser.id;
      aTaskId.taskId=this.selectedTask.id;

      let aTask=new AcceptedTask();
      aTask.id = aTaskId;

      this.acceptedTaskService.create(aTask).subscribe({
        next: (createdATask)=>{
          this.selectedTask=null;
        },
        error: (fail)=>{
          console.error('Error creating accepted task.');
          console.error(fail);
        }
      });
    }
  }

}

//classes for view types (easier to turn on and off at once)
class TaskView {
  name:string;
  visible?:boolean;

  constructor(
    name='',
    visible=false
  ){
    this.name=name;
    this.visible=false;
  }
}
