export class NotificationType {
  id: number;
  name: string;
  notifications: Notification[] | null;


  constructor(
    id: number = 0,
    name: string,
    notifications: Notification[] | null = null,

  ){
    this.id = id;
    this.name = name;
    this.notifications = notifications;

  }
}
