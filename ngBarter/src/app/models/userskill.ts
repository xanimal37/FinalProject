import { Skill } from "./skill";
import { Userskillid } from "./userskillid";

export class Userskill {
  id: Userskillid;
  certification:string;
  description:string;
  skill:Skill | null;

  constructor(
    id:Userskillid,
    certification:string='',
    description:string='',
    skill:Skill|null = null,
  ){
    this.id=id;
    this.certification=certification;
    this.description=description;
    this.skill=skill;
  }


}
