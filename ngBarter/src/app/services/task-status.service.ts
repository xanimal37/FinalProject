import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TaskStatus } from '../models/task-status';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskStatusService {

  private url = environment.baseUrl+'api/taskStatuses';

  taskStatuses: TaskStatus[] = [];

  constructor(private http: HttpClient) { }

  //GET api/tasks
  index(): Observable<TaskStatus[]> {
    return this.http.get<TaskStatus[]>(this.url).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('TaskStatusService.index(): error retrieving task statuses: '+ err)
        );
      })
    );
  };
}
