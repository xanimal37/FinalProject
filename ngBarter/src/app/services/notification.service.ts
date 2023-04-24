import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Notification} from '../models/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private url = environment.baseUrl + 'api/notifications';
  @Output() refreshNotifications: EventEmitter<any> = new EventEmitter();

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  indexNotificationsByUser(uId: number): Observable<Notification[]> {
    return this.http.get<Notification[]>(this.url + "/user/" + uId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('NotificationService.indexNotificationsByUser: error retrieving notifications: ' + err)
        );
      })
    );
  };

  createNotification(notification: Notification):Observable<Notification> {
    return this.http.post<Notification>(this.url, notification, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('NotificationService.createNotification(): error creating notification: ' + err)
        );
      })
    );
  };
}
