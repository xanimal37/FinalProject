import { User } from 'src/app/models/user';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   // Set port number to server's port
//  private baseUrl = 'http://localhost:8087/';
private url =environment.baseUrl;
@Output() refreshUsers: EventEmitter<any> = new EventEmitter();
  constructor(private http: HttpClient, private auth:AuthService) { }
  getHttpOptions() {
    // console.log(this.auth.getCredentials());

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
  getUserCount(): Observable<number> {
    return this.http.get<number>(this.url + "api/users/count").pipe(
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
    return this.http.put<User>(this.url+"api/users/"+ user.id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error('TodoService.create(): error creating Product: ' + err)
        );
      })
    );
    }
  updateEmail(user:User,email:string): Observable<User> {
    return this.http.put<User>(this.url+"api/users/"+ user.id+"/"+"email", email, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error('TodoService.create(): error creating Product: ' + err)
        );
      })
    );
    }

    disableUser(user: User): Observable<User> {
      console.log(user)
      return this.http.put<User>(this.url + "api/users/" + user.id + "/archive", user, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error('UserService.disableUser(): error disabling User: ' + err)
          );
        })
      );
      }

      enableUser(user: User): Observable<User>{
        console.log(user)
        return this.http.put<User>(this.url + "api/users/"+ user.id + "/unarchive", user, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.error(err);
            return throwError(
              () =>
                new Error('UserService.enableUser(): error enabling User: ' + err)
            );
          })
        );
        }


}
