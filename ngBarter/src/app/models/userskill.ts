import { Skill } from "./skill";
import { SkillLevel } from "./skill-level";
import { Userskillid } from "./userskillid";

export class Userskill {
  id: Userskillid;
  certification:string;
  description:string;
  skill:Skill;
  skillLevel:SkillLevel;

  constructor(
    id:Userskillid = new Userskillid(),
    certification:string='',
    description:string='',
    skill:Skill=new Skill(),
    skillLevel:SkillLevel=new SkillLevel(),
  ){
    this.id=id;
    this.certification=certification;
    this.description=description;
    this.skill=skill;
    this.skillLevel=skillLevel;
  }




}
