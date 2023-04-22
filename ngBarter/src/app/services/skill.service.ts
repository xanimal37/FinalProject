import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Skill } from '../models/skill';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  private url = environment.baseUrl+'api/skills';

  skills: Skill[] = [];

  constructor(private http: HttpClient) { }

  //GET api/skills
  index(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.url).pipe(
      catchError((err:any)=>{
        console.log(err);
        return throwError(
          ()=> new Error('SkillService.index(): error retrieving tasks: '+ err)
        );
      })
    );
  };
}
