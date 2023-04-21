import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Task } from '../models/task';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private url = environment.baseUrl+'api/tasks';

  tasks: Task[] = [];

  constructor(private http: HttpClient) { }


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
}
