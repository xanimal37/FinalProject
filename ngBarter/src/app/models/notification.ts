import { NotificationType } from './notification-type';
import { User } from './user';

export class Notification {
  id: number;
  message: string;
  createDate: string;
  type: NotificationType | null;
  user: User | null;

  constructor(
    id: number = 0,
    message: string = '',
    createDate: string = '',
    type: NotificationType | null = null,
    user: User  | null = null
  ){
    this.id = id;
    this.message = message;
    this.createDate = createDate;
    this.type = type;
    this.user = user;

  }
}
