import { Address } from "./address";
import { Ranking } from "./ranking";
import { Skill } from "./skill";
import { Task } from "./task";
import { Userskill } from "./userskill";

export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  imageURL: string;
  email: string;
  ranking: Ranking | null;
  friends: User[];
  // userSkills: UserSkill[];
  // address: Address;
  // comments: Comment[];
  tasks: Task[] | null;
  // taskMessages: TaskMessage[];
  // notifications: Notification[];
  // complaints: Complaint[];
  // posts: Post[];
  firstname: string;
  lastname: string;
  availability: string;
  phone: string;
  biography: string;
  address: Address | null;
  // skills:Skill[]|null;
  userSkills:Userskill[];




  //   }
  constructor(
    id:number = 0,
    username: string = '',
    password:string = '',
    role: string = '',
    email:string = '',
    ranking: Ranking | null = null,
    availability:string ='',
    biography:string ='',
    enabled:boolean= false,
    imageUrl:string= '',
    firstname:string='',
    lastname:string='',
    phone:string='',
    address:Address | null = null,
    tasks: Task[]=[],
    // skills: Skill[]=[],
    friends: User[]=[],
    userSkills: Userskill[]=[],




    // userSkills: UserSkill[] ,
    // comments: Comment[],
    // taskMessages: TaskMessage[],
    // notifications: Notification[],
    // complaints: Complaint[],
    // posts: Post[]


    )
    {
    this.id =id;
    this.username  = username ;
    this.password = password ;
    this.role = role ;
    this.enabled=enabled;
    this.email =email;
    this.ranking=ranking;
    this.imageURL = imageUrl;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone=phone;
    this.address = address;
    // this.skills = skills;
    this.friends = friends;
    // userSkills: UserSkill[] | null = null,
    // this.address = Address;
    this.availability = availability;
    this.biography = biography;
    // comments: Comment[],
    this.tasks = tasks;
    this.userSkills = userSkills;
    // taskMessages: TaskMessage[],
    // notifications: Notification[],
    // complaints: Complaint[],
    // posts: Post[]
    }
}







// biography

// createdDate

// email

// enabled

// firstname

// imageURL

// lastname

// password

// phone
// :

// ranking

// role

// updatedDate

// userSkills


// username
