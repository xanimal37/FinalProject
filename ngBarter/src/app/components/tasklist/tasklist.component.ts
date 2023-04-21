import { TaskService } from './../../services/task.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from 'src/app/models/task';

@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css']
})
export class TasklistComponent implements OnInit {

  title='Task List';
  tasks: Task[]=[];

  constructor(
  private taskService:TaskService,
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
            console.error('TaskListHttpComponent.loadTasks(): error retreiving tasks:');
            console.error(problem);
          }
        }
      );
    }
}
