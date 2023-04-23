import { AcceptedTaskId } from "./accepted-task-id";

export class AcceptedTask {

  //composite key
  id: AcceptedTaskId | null;

  ratingByRequestor: number;
  ratingByAcceptor: number;
  remarksByRequestor: string;
  remarksByAcceptor: string;

  constructor(
    id: AcceptedTaskId | null =null,
    ratingByRequestor: number=0,
    ratingByAcceptor: number=0,
    remarksByRequestor: string='',
    remarksByAcceptor: string=''
  ){
    this.id = id;
    this.ratingByRequestor = ratingByRequestor;
    this.ratingByAcceptor = ratingByAcceptor;
    this.remarksByAcceptor = remarksByAcceptor;
    this.remarksByRequestor = remarksByRequestor;
  }
}
