import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';
import { TaskStatusService } from 'src/app/services/task-status.service';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task';
import { FormGroup,FormControl } from '@angular/forms';

@Component({
  selector: 'app-mytasks',
  templateUrl: './mytasks.component.html',
  styleUrls: ['./mytasks.component.css']
})
export class MytasksComponent implements OnInit{

  @Input() loggedInUser: User | null = null;
  myTasks : Task[] =[];
  selectedTask : Task | null = null;
  updateTaskForm : FormGroup | null=null;

  constructor(
    private taskService:TaskService,
    private skillService:SkillService,
    private taskStatusService: TaskStatusService,
    private authService: AuthService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {

    this.loadTasks();
    this.buildForm();

  }

  loadTasks():void {
    this.taskService.getAllUsersTasks().subscribe(
        {
          next: (tasks) => {
            this.myTasks = tasks;
          },
          error: (problem) => {
            console.error('TaskListHttpComponent.loadTasks(): error retreiving tasks:');
            console.error(problem);
          }
        }
      );
    }

    delete(id: number){
      this.taskService.delete(id).subscribe({
        next:(updatedTask)=>{
          //things we want to do on success
          this.loadTasks();
        },
        error: (fail) => {
          console.error('Error deleting task.');
          console.error(fail);
        }
      });
    }

    update(task: Task){  //this is the todo that is being edited

      this.taskService.update(task).subscribe({
        next:(updatedTodo)=>{
          //things we want to do on success
          this.selectedTask=null;
          this.loadTasks();
        },
        error: (fail) => {
          console.error('Error updating task.');
          console.error(fail);
        }
      });
    }

    buildForm(){
      this.updateTaskForm = new FormGroup({
        'name': new FormControl(null),
        'description': new FormControl(null),
        'estimatedHours': new FormControl(0),
        'materialsProvided':new FormControl(false),
        'startDate':new FormControl('2023-04-01'),
        'skills':new FormArray([])

      });

      for(let skill of this.skills){
        this.newTaskForm.controls['skills'].push(new FormControl());
      }
    }



}
