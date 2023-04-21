import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user';
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
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  register(user: User): Observable<User> {
    // Create POST request to register a new account
    return this.http.post<User>(this.url + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
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

  // getUser(id: number): Observable<User> {
  //   const url = `${this.apiUrl}/${id}`;
  //   return this.http.get<User>(url);
  // }

  // addUser(user: User): Observable<User> {
  //   return this.http.post<User>(this.apiUrl, user, this.httpOptions);
  // }

  // updateUser(user: User): Observable<any> {
  //   const url = `${this.apiUrl}/${user.id}`;
  //   return this.http.put(url, user, this.httpOptions);
  // }

}
