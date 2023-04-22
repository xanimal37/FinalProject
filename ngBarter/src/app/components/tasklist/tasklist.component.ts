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
  //properties of task
  skills: Skill[] =[];
  taskStatuses: TaskStatus[] =[];
  skillsCheckBoxes:CheckBoxItem[] =[];
  requestedSkills: Skill[]=[];
  loggedInUser: User | undefined;

  constructor(
  private taskService:TaskService,
  private skillService:SkillService,
  private taskStatusService: TaskStatusService,
  private authService: AuthService,
  private datePipe: DatePipe,
  private route: ActivatedRoute,
  private router: Router)
  {}

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });

    this.loadTasks();
    this.loadSkills();
    this.loadTaskStatuses();
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

    loadSkills():void {
      this.skillService.index().subscribe(
          {
            next: (skills) => {
              this.skills = skills;
              this.createSkillsCheckboxes();
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


        //methods and extra class for adding a checklist

    addTask(task: Task){
    //  task.user = this.getLoggedInUser();
      this.setSelectedSkillsList(task);
      task.taskStatus=this.taskStatuses[0]; //pending
      console.log(task);
      this.taskService.create(task).subscribe({
        next: (createdTask)=>{
        //coming back from post method in controller
        //anything depending on asynchronous operations (needs server response)
        this.newTask=new Task(); //reset
        this.requestedSkills=[]; //reset
        this.loadTasks();
        },
        error: (fail) => {
          console.error('Error creating task.');
          console.error(fail);
        }
      });
    }

      setSelectedSkillsList(task:Task) {
        let selectedBoxes = this.skillsCheckBoxes.filter(item => item.checked);
        for(let box of selectedBoxes){
          for(let skill of this.skills){
            if(skill.id === box.id){
              task.skills?.push(skill);
            }
          }
        }
      }

       //check box objects to represent skills
       createSkillsCheckboxes(){
      for(let skill of this.skills){
      let checkbox = new CheckBoxItem();
      checkbox.id = skill.id;
      checkbox.name = skill.name;
      this.skillsCheckBoxes.push(checkbox);
    }
  }
}

//anything can use this class
//probably should be refactored to an interface
class CheckBoxItem {
  id:number;
  name:string;
  checked?:boolean;

  constructor(
    id:number = 0,
    name:string='',
    checked:boolean = false
    ){
    this.id=id;
    this.name = name;
    this.checked=checked;
  }


}
