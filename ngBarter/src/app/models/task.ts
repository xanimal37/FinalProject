import { Address } from "./address";
import { Skill } from "./skill";
import { TaskMessage } from "./task-message";
import { TaskStatus } from "./task-status";
import { User } from "./user";

export class Task {

	id:number;
	name: string;
	description: string;
	estimatedHours: number;
	materialsProvided: boolean;
	scheduleDate: string;
	startDate: string;
	completeDate: string;
	createDate: string;
	updateDate: string;

	//relationships
  address: Address | null;
	taskStatus: TaskStatus | null;
	taskMessages: TaskMessage[] | null;
	user: User | null;
	skills: Skill[] | null;


  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    estimatedHours: number=0,
    materialsProvided: boolean = false,
    scheduleDate: string = '',
    startDate: string = '',
    completeDate: string = '',
    createDate: string='',
    updateDate='',


    address: Address | null = null,
    taskStatus: TaskStatus | null = null,
    taskMessages: TaskMessage[] = [],
    user: User | null = null,
    skills: Skill[] = [],
  ){
    this.id = id;
    this.name = name;
    this.description=description;
    this.estimatedHours=estimatedHours;
    this.materialsProvided=materialsProvided;
    this.scheduleDate=scheduleDate;
    this.startDate=startDate;
    this.completeDate=completeDate;
    this.createDate=createDate;
    this.updateDate=updateDate;

    this.address=address;
    this.taskStatus=taskStatus;
    this.taskMessages=taskMessages;
    this.user = user;
    this.skills=skills;
  }

}
