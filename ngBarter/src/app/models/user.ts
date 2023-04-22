import { Address } from "./address";

export class User {
  id:number;
  username: string;
  password:string;
  role: string;
  email: string;
  availability:string;
  biography:string;
  // ranking: Ranking | null;
  // friends:User[];
  friends: any[] | undefined;


  // userSkills: UserSkill[] | null;
  address: Address | null = null;
  // comments: Comment[] | null;
  // tasks: Task[] | null;
  // taskMessages: TaskMessage[] | null;
  // notifications: Notification[] | null;
  // complaints: Complaint[] | null;
  // posts: Post[] | null;


  constructor(
    id:number = 0,
    username: string = '',
    password:string = '',
    role: string = '',
    email:string = '',
    availability ='',
    biography ='',
    friends:User[] | undefined = [],

    // userSkills: UserSkill[] ,
    // address: Address,
    // comments: Comment[],
    // tasks: Task[],
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
    this.email =email;
    this.friends=friends;
    // userSkills: UserSkill[] | null = null,
    this.address = Address;
    this.availability = availability;
    this.biography = biography;
    // comments: Comment[],
    // tasks: Task[],
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
