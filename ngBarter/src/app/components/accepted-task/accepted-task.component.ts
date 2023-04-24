import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AcceptedTask } from 'src/app/models/accepted-task';
import { AcceptedTaskId } from 'src/app/models/accepted-task-id';
import { User } from 'src/app/models/user';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { Task } from 'src/app/models/task';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-accepted-task',
  templateUrl: './accepted-task.component.html',
  styleUrls: ['./accepted-task.component.css']
})
export class AcceptedTaskComponent implements OnInit {

  tasks: Task[]=[];
  acceptedTasks: AcceptedTask[]=[];
  @Input() loggedInUser: User | null =null;
  selectedTask:Task | null = null;
  selectedAcceptedTask: AcceptedTask | null = null;

  constructor(
    private acceptedTaskService: AcceptedTaskService,
    private taskService: TaskService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router){}

    ngOnInit(): void {
      this.loadAcceptedTasks();
    }

    loadTasks():void {
      this.taskService.index().subscribe(
          {
            next: (tasks) => {
              this.tasks = tasks;
            },
            error: (problem) => {
              console.error('TaskListHttpComponent.loadTasks(): error retreiving tasks:');
              console.error(problem);
            }
          }
        );
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

    getTaskName(id: number):string{
      let taskname = '';
      for(let i=0;i<this.tasks.length;i++){
        if(this.tasks[i].id == id){
          taskname = this.tasks[i].name;
        }
      }
      return taskname;
    }

    setSelectedTask(aTask: AcceptedTask): void{

      let taskId = aTask.id?.taskId;
      this.selectedTask=null;
      if(typeof taskId=='number'){
      for(let i=0;i<this.tasks.length;i++){
        if(this.tasks[i].id = taskId){
          this.selectedTask=this.tasks[i];
          break;
        }
      }
        }
      }

      selectAcceptedTask(aTask:AcceptedTask){
        this.selectedAcceptedTask=aTask;
        this.setSelectedTask(aTask);
      }
}
