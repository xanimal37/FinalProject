export class AcceptedTaskId {

  taskId :number;
  acceptorId : number;

	constructor(
    taskId: number =0,
    acceptorId: number =0
  )
  {
    this.taskId = taskId;
    this.acceptorId=acceptorId;
  }
}
