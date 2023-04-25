import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { NotificationService } from 'src/app/services/notification.service';
import { Notification } from 'src/app/models/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  loggedInUser: User | undefined;
  admin: string = "admin";
  notifications: Notification[] = [];

  constructor(
    private auth: AuthService,
    private notification: NotificationService,
    private router: Router

    ){
    }
    isCollapsed: boolean = false;

  ngOnInit(): void {
    this.verifyUser();
    this.auth.getLoggedIn.subscribe(user => this.loggedInUser = user)
    this.notification.refreshNotifications.subscribe(notifications => this.notifications = notifications)

   }

  loggedIn():boolean{
    return this.auth.checkLogin();
  }

  loadNotificationsByUser(uId: number) {
    this.notification.indexNotificationsByUser(uId).subscribe({
      next: (notificationList) => {
        this.notifications = notificationList;
      },
      error: (err) => {
        console.error('Error getting complaints list');
        console.error(err);
      }
    });
  }
  viewUserProfile(user:User) {
    this.router.navigate(['/user-profile', this.loggedInUser!.id]);

  }

  verifyUser(): void{
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.loggedInUser = user;
        this.loadNotificationsByUser(this.loggedInUser.id);
      },
      error: (nojoy) => {
        console.log(nojoy);
      }

     });
    }
}
