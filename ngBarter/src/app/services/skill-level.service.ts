import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Skill } from '../models/skill';

@Injectable({
  providedIn: 'root'
})
export class SkillLevelService {


  private url = environment.baseUrl+'api/skills/skillLevel';

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
