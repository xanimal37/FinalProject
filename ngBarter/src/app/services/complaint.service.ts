import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Complaint } from '../models/complaint';
import { Observable, catchError, throwError } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private url = environment.baseUrl + 'api/complaints';

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

    //All posts
    indexAll(): Observable<Post[]> {
      return this.http.get<Post[]>(this.url, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('PostService.indexAll(): error retrieving Posts: ' + err)
          );
        })
      );
    };

  //Post by Post Id
    indexByPostId(pId: number): Observable<Post> {
      return this.http.get<Post>(this.url + "/" + pId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('PostService.index(): error retrieving Post: ' + err)
          );
        })
      );
    };

  //All posts by user
    indexByUser(uId: number): Observable<Post[]> {
      return this.http.get<Post[]>(this.url + "/user/" + uId, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('PostService.index(): error retrieving Posts: ' + err)
          );
        })
      );
    };


    create(complaint: Complaint):Observable<Complaint> {
      return this.http.post<Complaint>(this.url, complaint, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('PostService.index(): error retrieving Post: ' + err)
          );
        })
      );
    };


    update(complaint: Complaint, id: number):Observable<Complaint> {
      return this.http.put<Complaint>(this.url + "/" + id, complaint, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('PostService.index(): error updating complaing: ' + err)
          );
        })
      );
    };

    archive(id: Number): Observable<void> {
      return this.http.put<void>(this.url + "/archive/" + id, this.getHttpOptions()). pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('TodoService.create(): error deletingtodo: ' + err)
          );
        })
      )
    }

}
