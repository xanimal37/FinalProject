import { Pipe, PipeTransform } from '@angular/core';
import { Task } from '../models/task';

@Pipe({
  name: 'taskbyskill'
})
export class TaskbyskillPipe implements PipeTransform {

  transform(alltasks: Task[], skillname: string): Task[] {
    let tasks: Task[] =[];
    if(skillname==='all'){
      return alltasks;
    }
    alltasks.forEach((task)=>{
      if(task.skills){
        for(let i=0;i<task.skills.length;i++){
          if(task.skills[i].name==skillname){
            tasks.push(task);
          }
        }
      }
    });
    return tasks;
  }

}
