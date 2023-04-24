import { Injectable } from '@angular/core';
import { Userskill } from '../models/userskill';
import { Observable, catchError, throwError } from 'rxjs';
import { Complaint } from '../models/complaint';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserSkillService {

  private url = environment.baseUrl + 'api/userSkill';

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
  createUserSkill(userSkill: Userskill):Observable<Userskill> {
    return this.http.post<Userskill>(this.url, userSkill, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserSkillService.createComplaint(): error creating complaint: ' + err)
        );
      })
    );
  };
}
