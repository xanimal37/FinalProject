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

   //GET "users/tasks"
   getAllUsersTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(environment.baseUrl+"api/users/tasks", this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('TaskService.index(): error retrieving user tasks: '+ err)
        );
      })
    );
  };

   //GET "users/tasks"
   getTaskById(id: number): Observable<Task> {
    return this.http.get<Task>(environment.baseUrl+"api/users/tasks/"+id, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('TaskService.index(): error retrieving user tasks: '+ err)
        );
      })
    );
  };

  update(task: Task):Observable<Task> {
    return this.http.put<Task>(this.url + "/" + task.id, task,this.getHttpOptions()).pipe(
      catchError((err:any)=> {
        console.log(err);
        return throwError(
          ()=>new Error("TaskService.update(): error updating todo: "+err)
        );
      })
    );
  };

  delete(taskId: number):Observable<void> {
    return this.http.delete<void>(this.url + "/" + taskId,this.getHttpOptions()).pipe(
      catchError((err:any)=> {
        console.log(err);
        return throwError(
          ()=>new Error("TaskService.delete(): error deleting task: "+err)
        );
      })
    );
  }
}
