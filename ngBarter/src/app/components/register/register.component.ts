import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { Userskill } from 'src/app/models/userskill';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private auth:AuthService , private router:Router , private skillService:SkillService){

  }
  ngOnInit(): void {
    this.loadSkills();
  }
  selectedSkillName:string='all';
  newUser: User = new User();
  skills: Skill[] =[];
  // skillsCheckBoxes:CheckBoxItem[] =[];
register(user: User): void {

  console.log('Registering user:');

  this.auth.register(user).subscribe({
    next: (registeredUser) => {
      this.auth.login(user.username, user.password).subscribe({
        next: (loggedInUser) => {
          console.log(loggedInUser);

          this.router.navigateByUrl('/userAccount');

        },
        error: (problem) => {
          console.error('RegisterComponent.register(): Error logging in user:');
          console.error(problem);
        }
      });
    },
    error: (fail) => {
      console.error('RegisterComponent.register(): Error registering account');
      console.error(fail);
    }
  });

}
// setSelectedSkillsList(user: User) {
//   let selectedBoxes = this.skillsCheckBoxes.filter(item => item.checked);
//   for (let box of selectedBoxes) {
//     let skill = this.skills.find(s => s.id === box.id);
//     if (skill) {
//       let userSkill: Userskill = {
//         certification: '',
//         skill: skill,
//         id: { userId: user.id, skillId: skill.id },
//         description: ''
//       };
//       user.userSkills?.push(userSkill);
//     }
//   }

// }

loadSkills():void {
  this.skillService.index().subscribe(
      {
        next: (skills) => {
          this.skills = skills;
        },
        error: (problem) => {
          console.error('TaskListHttpComponent.loadSkills(): error retreiving skills:');
          console.error(problem);
        }
      }
    );
  }

// createSkillsCheckboxes(){
//   for(let skill of this.skills){
//   let checkbox = new CheckBoxItem();
//   checkbox.id = skill.id;
//   checkbox.name = skill.name;
//   this.skillsCheckBoxes.push(checkbox);
// }
}


// class CheckBoxItem {
//   id:number;
//   name:string;
//   checked?:boolean;

//   constructor(
//     id:number = 0,
//     name:string='',
//     checked:boolean = false
//     ){
//     this.id=id;
//     this.name = name;
//     this.checked=checked;
//   }
// }




