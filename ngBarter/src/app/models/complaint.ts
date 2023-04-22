import { User } from "./user";

export class Complaint {

  id: number;
  name: string;
  description: string;
  active: boolean;
  createDate: string;
  updateDate: string;
  closedDate: string;
  user: User | null;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    active: boolean = true,
    createDate: string = '',
    updateDate: string = '',
    closedDate: string = '',
    comments: Comment[] | null = null,
    user: User  | null = null
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.closedDate = closedDate;
    this.user = user;

  }


}
