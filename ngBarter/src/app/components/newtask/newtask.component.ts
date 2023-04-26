import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Skill } from 'src/app/models/skill';
import { Task } from 'src/app/models/task';
import { SkillService } from 'src/app/services/skill.service';
import { TaskService } from 'src/app/services/task.service';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-newtask',
  templateUrl: './newtask.component.html',
  styleUrls: ['./newtask.component.css']
})

export class NewtaskComponent implements OnInit {

  newTaskForm:FormGroup | any;
  skills: Skill[] = [];
  materialsProvided: string[] = ['yes','no'];
  newTask: Task = new Task();

  constructor(
    private taskService: TaskService,
    private skillService:SkillService){
  }

  ngOnInit(){
    this.loadSkills();
    this.buildForm();
  }

  close() {

  }

  @Output('closeChild') closeChild: EventEmitter<any> = new EventEmitter();

  loadSkills():void{
    this.skillService.index().subscribe(
      {
        next: (skills) => {
          this.dothings(skills);


        },
        error: (problem) => {
          console.error('TaskListHttpComponent.loadSkills(): error retreiving skills:');
          console.error(problem);
        }
      }
    );
  }

  onSubmit(){
    this.createTask();
  }

  dothings(skills:Skill[]){
    this.skills=skills;
    this.buildForm();
  }

  buildForm(){
    this.newTaskForm = new FormGroup({
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


  createTask(){

      this.newTask.name=this.newTaskForm.controls['name'].value;
      this.newTask.description=this.newTaskForm.controls['description'].value;
      this.newTask.estimatedHours=this.newTaskForm.controls['estimatedHours'].value;
      this.newTask.materialsProvided=this.newTaskForm.controls['materialsProvided'].value;

      let dateString = this.newTaskForm.controls['startDate'].value;
      dateString+='T01:00:00';
      this.newTask.startDate=dateString;

      for(let i=0; i<this.newTaskForm.controls['skills'].controls.length;i++){

        if(this.newTaskForm.controls['skills'].controls[i].value!=null){
          this.newTask.skills?.push(this.skills[i]);
        }


      }



      this.taskService.create(this.newTask).subscribe( {
        next: (createdTask) => {
          this.newTask = new Task();
          this.closeChild.emit();
          location.reload();

        },
        error: (fail) => {
          console.error('Error creating task');
          console.error(fail);
        }
      });
    }
}

