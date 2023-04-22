import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment.development';
import { Observable, catchError, throwError } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = environment.baseUrl + 'api/posts';

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

  //Keyword Search
  postKeywordSearch(keyword: number): Observable<Post[]> {
    return this.http.get<Post[]>(this.url + "/search/" + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.index(): error retrieving Keyword Search: ' + err)
        );
      })
    );
  };

  //All Comments for specific posts
  postComments(pId: number): Observable<Post[]> {
    return this.http.get<Post[]>(this.url + "/comments/" + pId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.index(): error retrieving Posts: ' + err)
        );
      })
    );
  };

  create(post: Post):Observable<Post> {
    post.enabled = true;
    return this.http.post<Post>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.index(): error retrieving Post: ' + err)
        );
      })
    );
  };


  update(post: Post, id: number):Observable<Post> {
    post.enabled = true;
    return this.http.put<Post>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.index(): error retrieving Post: ' + err)
        );
      })
    );
  };

  disable(id: Number): Observable<void> {
    return this.http.put<void>(this.url + "/" + id, this.getHttpOptions()). pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create(): error deletingtodo: ' + err)
        );
      })
    )
  }


}
