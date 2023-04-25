import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { TaskMessage } from '../models/task-message';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TaskMessageService {

  private url = environment.baseUrl+'api/taskMessages';

  constructor(private authService: AuthService,
    private http: HttpClient) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  //POST api/taskMessages
  create(tmsg: TaskMessage, tid:number): Observable<TaskMessage> {
    return this.http.post<TaskMessage>(environment.baseUrl + "api/tasks/"+tid+"/taskMessages", tmsg, this.getHttpOptions()).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=>new Error('TaskService.create(): error creating task: '+err)
        );
      })
    );
    };
}
