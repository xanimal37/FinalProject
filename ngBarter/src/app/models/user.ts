export class User {
  id:number;
  username: string;
  password:string;
  role: string;
  email: string;
  ranking: Ranking | null;
  friends: User[];
  userSkills: UserSkill[] | null;
  address: Address | null;
  comments: Comment[] | null;
  tasks: Task[] | null;
  taskMessages: TaskMessage[] | null;
  notifications: Notification[] | null;
  complaints: Complaint[] | null;
  posts: Post[] | null;


  constructor(
    id:number = 0,
    username: string = '',
    password:string = '',
    role: string = '',
    email:string = '',
    friends: User[],
    userSkills: UserSkill[] ,
    address: Address,
    comments: Comment[],
    tasks: Task[],
    taskMessages: TaskMessage[],
    notifications: Notification[],
    complaints: Complaint[],
    posts: Post[]


    )
    {
    this.id =id;
    this.username  = username ;
    this.password = password ;
    this.role = role ;
    this.email =email;
    userSkills: UserSkill[] | null = null,
    address: Address,
    comments: Comment[],
    tasks: Task[],
    taskMessages: TaskMessage[],
    notifications: Notification[],
    complaints: Complaint[],
    posts: Post[]
    }
}
