import { User } from 'src/app/models/user';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   // Set port number to server's port
//  private baseUrl = 'http://localhost:8087/';
private url =environment.baseUrl;

  constructor(private http: HttpClient, private auth:AuthService) { }
  getHttpOptions() {
    console.log(this.auth.getCredentials());

    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  getUsers(): Observable<User[]> {

    return this.http.get<User[]>(this.url + "api/users", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error retrieving Todos: ' + err)
        );
      })
    );
  }

  getUserById(userId:number): Observable<User> {

    return this.http.get<User>(this.url + "api/users/"+userId,  this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error retrieving Todos: ' + err)
        );
      })
    );
  }

  addFriend(userId:number,user: User): Observable<User> {
    console.log(this.getHttpOptions());

    return this.http.post<User>(this.url + "api/users/friends/"+userId, user , this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error( err)
        );
      })
    );
  }

  updateUser(user:User): Observable<User> {
    return this.http.put<User>("http://localhost:8088/api/users/"+ user.id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error('TodoService.create(): error creating Product: ' + err)
        );
      })
    );
    }

}
