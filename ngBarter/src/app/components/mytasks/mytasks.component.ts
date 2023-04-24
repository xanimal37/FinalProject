import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';
import { TaskStatusService } from 'src/app/services/task-status.service';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task';
import { FormArray,FormControl,FormGroup } from '@angular/forms';
import { Skill } from 'src/app/models/skill';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-mytasks',
  templateUrl: './mytasks.component.html',
  styleUrls: ['./mytasks.component.css']
})
export class MytasksComponent implements OnInit{

  @Input() loggedInUser: User | null = null;
  myTasks : Task[] =[];
  selectedTask : Task | null = null;
  updateTaskForm : FormGroup | any;
  skills: Skill[] = [];

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

    this.loadUserTasks();
    this.loadSkills();

  }

  loadUserTasks():void {
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

    loadSkills():void {
      this.skillService.index().subscribe(
          {
            next: (skills) => {
                this.createForm(skills);
            },
            error: (problem) => {
              console.error('TaskListHttpComponent.loadSkills(): error retreiving skills:');
              console.error(problem);
            }
          }
        );
      }

    delete(id: number){
      this.taskService.delete(id).subscribe({
        next:(updatedTask)=>{
          //things we want to do on success
          this.loadUserTasks();
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
          this.loadUserTasks();
        },
        error: (fail) => {
          console.error('Error updating task.');
          console.error(fail);
        }
      });
    }

    createForm(skills: Skill[]){
      this.skills=skills;
      this.buildForm();
    }

    buildForm(){
      this.updateTaskForm = new FormGroup({
        'description': new FormControl(),
        'estimatedHours': new FormControl(0),
        'materialsProvided':new FormControl(false),
        'startDate':new FormControl('2023-04-01'),
        'skills':new FormArray([])

      });

      for(let skill of this.skills){
        this.updateTaskForm.controls['skills'].push(new FormControl());
      }
    }

    fillForm(task:Task){
      this.updateTaskForm.controls['description'].setValue(task.description);
      this.updateTaskForm.controls['estimatedHours'].setValue(task.estimatedHours);
      if(task.materialsProvided===true){
        this.updateTaskForm.controls['materialsProvided'].setValue(true);
      }

      this.updateTaskForm.controls['startDate'].setValue(formatDate(task.startDate, 'yyyy-MM-dd', 'en'));

      if(task.skills!=null){
      for(let i=0;i<this.updateTaskForm.controls['skills'].controls.length;i++){
        for(let skill of task.skills){
          if(skill.id === this.skills[i].id){
            this.updateTaskForm.controls['skills'].controls[i].setValue(true);
          }
        }
      }

      }


    }

    selectTask(task:Task){
      this.selectedTask=task;
      this.fillForm(task);
    }

    updateTask(task:Task){

      task.description=this.updateTaskForm.controls['description'].value;
      task.estimatedHours=this.updateTaskForm.controls['estimatedHours'].value;
      task.materialsProvided=this.updateTaskForm.controls['materialsProvided'].value;

      let dateString = this.updateTaskForm.controls['startDate'].value;
      dateString+='T01:00:00';
      task.startDate=dateString;

      task.skills=[]; //clear tasks

      //re-add
      for(let i=0; i<this.updateTaskForm.controls['skills'].controls.length;i++){
        if(this.updateTaskForm.controls['skills'].controls[i].value==true){
          task.skills?.push(this.skills[i]);
        }


      }

      this.taskService.update(task).subscribe( {
        next: (task) => {
          this.selectedTask=null;
        },
        error: (fail) => {
          console.error('Error creating task');
          console.error(fail);
        }
      });
    }

}
