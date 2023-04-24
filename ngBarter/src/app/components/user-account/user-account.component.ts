import { UserService } from 'src/app/services/user.service';
import { SkillLevelService } from './../../services/skill-level.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Skill } from 'src/app/models/skill';
import { SkillLevel } from 'src/app/models/skill-level';
import { User } from 'src/app/models/user';
import { Userskill } from 'src/app/models/userskill';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';
import { UserSkillService } from 'src/app/services/user-skill.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent {
  constructor(
    private auth:AuthService ,
    private router:Router ,
    private skillService:SkillService,
    private userSkillService:UserSkillService,
    private skillLevelService : SkillLevelService,
    private userService : UserService,
    ){

    }
    editUser: User | null = null;
    isEditEmail: boolean = true;
    selectedSkillName:string='all';
    user: User = new User();
    skills: Skill[] =[];
    skillLevels: SkillLevel[] =[];
    newUserSkill: Userskill = new Userskill();
    editedUsername: string |null = null;
    editedEmail: string |null = null;
    editedBiography: string |null = null;

    ngOnInit(): void {
      this.getLoggedInUser()
      this.loadSkills();
      this.loadSkillLevels();
    }
    setItem(user: User) {
      this.editUser = user;

    }
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
  loadSkillLevels():void {
    this.skillLevelService.index().subscribe(
        {
          next: (skillLevels) => {
            this.skillLevels = skillLevels;
          },
          error: (problem) => {
            console.error('SkillLevelHttpComponent.loadSkills(): error retreiving skills:');
            console.error(problem);
          }
        }
      );
    }

    createUserSkill(userSkill: Userskill) {
      this.userSkillService.createUserSkill(userSkill).subscribe( {
        next: (createdUserSkill) => {
          this.newUserSkill = new Userskill();
          this.reload();
          // this.reload();
        },
        error: (fail) => {
          console.error('Error creating complaint');
          console.error(fail);
        }
      });

      // this.reload();
    }
    updateAccount(user: User, Id: number) {
      this.userService.updateUser(user).subscribe( {
        next: (user) => {
          this.user = user;
          this.reload();
        },
        error: (fail) => {
          console.error('Error editing post');
          console.error(fail);
        }
      });
      this.reload();
    }

    getLoggedInUser(){
      this.auth.getLoggedInUser().subscribe({
        next:(user:User)=>{
          this.user = user;
        },
        error: (nojoy) => {
          console.log(nojoy);
        }
      })
    }

    reload() {
      this.getLoggedInUser();
    }

    editUsername() {
      if (this.editedUsername) {
        this.user.username = this.editedUsername;
        this.userService.updateUser(this.user);
        this.editedUsername = '';
      }
    }
    editEmail() {
      if (this.editedEmail) {
        this.isEditEmail = true;
        this.editedEmail = this.user.email;

      }
    }
    saveEmail(){
      this.userService.updateUser(this.user).subscribe(() => {
        this.editedEmail = this.user.email
        this.isEditEmail = false;
      });
    }

    editBiography() {
      if (this.editedBiography) {
        this.user.biography = this.editedBiography;
        this.userService.updateUser(this.user);
        this.editedBiography = '';
      }
    }


}
