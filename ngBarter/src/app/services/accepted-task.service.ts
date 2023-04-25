import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AcceptedTask } from '../models/accepted-task';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Task } from '../models/task';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AcceptedTaskService {

  private url = environment.baseUrl+'api/acceptedTasks';

  acceptedTasks: AcceptedTask[] = [];

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

  //GET api/users/acceptedTasks
  index(): Observable<AcceptedTask[]> {
    return this.http.get<AcceptedTask[]>(environment.baseUrl+"api/users/acceptedTasks", this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('AcceptedTaskService.index(): error retrieving accepted tasks: '+ err)
        );
      })
    );
  };

  //GET api/acceptedTasks
  indexAll(): Observable<AcceptedTask[]> {
    return this.http.get<AcceptedTask[]>(environment.baseUrl+"api/acceptedTasks", this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('AcceptedTaskService.index(): error retrieving accepted tasks: '+ err)
        );
      })
    );
  };

  //POST api/users/acceptedTasks
  create(acceptedTask: AcceptedTask): Observable<AcceptedTask> {
    return this.http.post<AcceptedTask>(environment.baseUrl+"api/users/acceptedTasks", acceptedTask, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=>new Error('TaskService.create(): error creating accepted task: '+err)
        );
      })
    );
    };

    //put
    update(aTask: AcceptedTask):Observable<AcceptedTask> {
      let taskid = aTask.id?.taskId;
      return this.http.put<AcceptedTask>(environment.baseUrl + "api/users/acceptedTasks/"+ taskid, aTask,this.getHttpOptions()).pipe(
        catchError((err:any)=> {
          console.log(err);
          return throwError(
            ()=>new Error("AcceptedTaskService.update(): error updating accepted task: "+err)
          );
        })
      );
    };




}
