import { Component, Input, OnChanges } from '@angular/core';
import { AcceptedTask } from 'src/app/models/accepted-task';
import { Task } from 'src/app/models/task';
import { AcceptedTaskService } from 'src/app/services/accepted-task.service';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-single-accepted-task',
  templateUrl: './single-accepted-task.component.html',
  styleUrls: ['./single-accepted-task.component.css']
})
export class SingleAcceptedTaskComponent implements OnChanges{

  @Input() task: Task | null=null;
  @Input() acceptedTasks : AcceptedTask[] =[];
  acceptedTask : AcceptedTask | null=null;
  acceptedTaskBeingRated: AcceptedTask | null = null;

  ngOnChanges() {
    if(this.task!=null){
    this.acceptedTask=this.getAcceptedTask(this.task);
    console.log("on changes called!");
    console.log(this.task.id);
    console.log(this.acceptedTask?.id?.taskId);
  }
  }

  constructor(private acceptedTaskService:AcceptedTaskService,
    private taskService: TaskService){}

  getAcceptedTask(task: Task): AcceptedTask | null
  {
    console.log("getting accepted task");
  for(let aTask of this.acceptedTasks){
    if(task.id==aTask.id?.taskId){
      return aTask;
    }
  }
  return null;
  }

  rateAcceptedTask(acceptedTask:AcceptedTask | null){
    if(acceptedTask!=null){
    this.acceptedTaskBeingRated=acceptedTask;
    }
  }

  submitRating(aTask:AcceptedTask){

      this.acceptedTaskService.update(aTask).subscribe({
        next:(updatedATask)=>{
          //things we want to do on success
          this.acceptedTaskBeingRated=null;
          if(this.task!=null){
            this.updateTask(this.task);
          }

        },
        error: (fail) => {
          console.error('Error updating task.');
          console.error(fail);
        }
      });
    }

    updateTask(task: Task){

      this.taskService.update(task).subscribe({
        next:(task)=>{
          //things we want to do on success
        },
        error: (fail) => {
          console.error('Error updating task.');
          console.error(fail);
        }
      });
    }
  }

