import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Task } from '../models/task';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private url = environment.baseUrl+'api/tasks';

  tasks: Task[] = [];

  constructor(
    private http: HttpClient,
    private authService: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  //GET api/tasks
  index(): Observable<Task[]> {
    return this.http.get<Task[]>(this.url).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('TaskService.index(): error retrieving tasks: '+ err)
        );
      })
    );
  };

  //POST api/tasks
  create(task: Task): Observable<Task> {
    return this.http.post<Task>(environment.baseUrl+"api/tasks", task, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=>new Error('TaskService.create(): error creating task: '+err)
        );
      })
    );
    };
}
