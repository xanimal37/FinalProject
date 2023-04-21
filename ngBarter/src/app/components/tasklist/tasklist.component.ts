import { TaskService } from './../../services/task.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from 'src/app/models/task';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css']
})
export class TasklistComponent implements OnInit {

  title='Task List';
  tasks: Task[]=[];
  newTask: Task = new Task();
  addingTask:boolean = false;

  constructor(
  private taskService:TaskService,
  private datePipe: DatePipe,
  private route: ActivatedRoute,
  private router: Router)
  {}

  ngOnInit(): void {
    this.reload();
  }

  reload():void {
    this.taskService.index().subscribe(
        {
          next: (tasks) => {
            this.tasks = tasks;
          },
          error: (problem) => {
            console.error('TaskListHttpComponent.reload(): error retreiving tasks:');
            console.error(problem);
          }
        }
      );
    }

    addTask(task: Task){
      this.taskService.create(task).subscribe({
        next: (createdTask)=>{
        //coming back from post method in controller
        //anything depending on asynchronous operations (needs server response)
        this.newTask=new Task();
        this.reload();
        },
        error: (fail) => {
          console.error('Error creating task.');
          console.error(fail);
        }
      });
      }
}
