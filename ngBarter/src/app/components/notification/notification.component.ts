import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {

  title: string = "Notifications";
  notification: Notification | null = null;
  selected: Notification | null =  null;
  selectedNotification: Notification[] | null = null;
  newNotification: Notification = new Notification;
  editNotification: Notification | null = null;
  notifications: Notification[] = []
  showEnabled: boolean = false;
  loggedInUser: User = new User();
  admin: string = "admin";
  active: string = "active";
  closed: string = "closed";


  constructor(
    private notificationService: NotificationService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    ) {
    }

  displayNotification(notification: Notification) {
    this.selected = notification;
  }

  displayTable():void {
    this.selected = null;
  }

  setEditNotification(notification: Notification): void {
    this.editNotification = notification;
  }
  cancelEditNotification(): void {
    this.editNotification = null;
  }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });
     this.reloadNotificationsByUser(this.loggedInUser.id);
   }

   reloadNotificationsByUser(uId: number) {
    this.notificationService.indexNotificationsByUser(uId).subscribe({
      next: (notificationtList) => {
        this.notifications = notificationtList;
      },
      error: (err) => {
        console.error('Error getting complaints list');
        console.error(err);
      }
    });
  }

  createNotification(notification: Notification) {
    this.notificationService.createNotification(notification).subscribe( {
      next: (createdNotification) => {
        this.newNotification = createdNotification;
      },
      error: (fail) => {
        console.error('Error creating complaint');
        console.error(fail);
      }
    });
  }

}
