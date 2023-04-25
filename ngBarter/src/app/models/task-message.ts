export class TaskMessage {

  id: number;
  title: string;
  content: string;
  createDate: string;
  taskId: number;
  userId: number;

  constructor(
    id: number=0,
    title:string='',
    content: string='',
    createDate:string='',
    taskId:number=0,
    userId:number=0
  ){
    this.id=id;
    this.title=title;
    this.createDate= createDate;
    this.taskId=taskId;
    this.content=content;
    this.userId=userId;
  }
}
