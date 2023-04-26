import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AcceptedTask } from 'src/app/models/accepted-task';
import { User } from 'src/app/models/user';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { Task } from 'src/app/models/task';
import { TaskService } from 'src/app/services/task.service';
import { TaskMessage } from 'src/app/models/task-message';
import { TaskMessageService } from 'src/app/services/task-message.service';

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
  ratingRequestor: boolean = false;

  newTaskMessage: TaskMessage = new TaskMessage();
  creatingTaskMessage: boolean = false;
  refTask: Task | null =null;

  constructor(
    private acceptedTaskService: AcceptedTaskService,
    private taskMessageService: TaskMessageService,
    private taskService: TaskService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router){}

    ngOnInit(): void {
      this.loadTasks();
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

    getTaskName(aTask: AcceptedTask):string{
      let taskname = 'unk';
      if(aTask.id!=null){
        for(let task of this.tasks){
          if(task.id === aTask.id.taskId){
            taskname = task.name;
            break;
          }
        }
      }

      return taskname;
    }

    setSelectedTask(aTask: AcceptedTask): void{

      let taskId = aTask.id?.taskId;
      this.selectedTask=null;
      console.log(aTask.id?.taskId);
      if(typeof taskId=='number'){
      for(let i=0;i<this.tasks.length;i++){
        if(this.tasks[i].id == taskId){
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

      checkVerifiedTask(aTask: AcceptedTask):boolean{
        let verified=false;
      if(aTask.id!=null){
        for(let task of this.tasks){
          if(task.id === aTask.id.taskId){
            if(task.taskStatus?.name=='Verified'){
              verified=true;
            }
            break;
          }
        }
      }

      return verified;
      }

      update(aTask:AcceptedTask){
          this.acceptedTaskService.update(aTask).subscribe({
            next:(updatedATask)=>{
              //things we want to do on success
              this.selectedTask=null;
              this.selectedAcceptedTask=null;
              this.loadAcceptedTasks();
            },
            error: (fail) => {
              console.error('Error updating task.');
              console.error(fail);
            }
          });
        }

        createTaskMessage(tmsg: TaskMessage){
          let tid = this.refTask?.id;
          if(typeof tid == 'number'){
          this.taskMessageService.create(tmsg,tid).subscribe( {
            next: (tmsg) => {
              this.newTaskMessage = new TaskMessage();
            this.creatingTaskMessage= false;
            this.refTask= null;
            this.loadAcceptedTasks();
            location.reload();
            },
            error: (fail) => {
              console.error('Error creating task');
              console.error(fail);
            }
          });
        }
      }

  setReferenceTask(aTask: AcceptedTask){

    if(aTask.id!=null){
      for(let task of this.tasks){
        if(task.id == aTask.id.taskId){
          this.refTask=task;
          break;
        }
      }
  }
  }

  getTaskMessages(aTask:AcceptedTask){
    let thisTask = null;
    if(aTask!=null){
      for(let task of this.tasks){
        if(task.id == aTask.id?.taskId){
          thisTask=task;
          break;
        }
      }
    }
    return thisTask?.taskMessages;
  }
}

