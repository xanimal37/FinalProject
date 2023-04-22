import { Skill } from "./skill";

export class Userskill {
  id: number;
  certification:string;
  description:string;
  skill:Skill | null;

  constructor(
    id:number=0,
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
