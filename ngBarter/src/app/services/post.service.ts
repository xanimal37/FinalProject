import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Post } from '../models/post';
import { Comment} from '../models/comment';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = environment.baseUrl + 'api/posts';
  @Output() refreshPosts: EventEmitter<any> = new EventEmitter();

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
    return this.http.get<Post[]>(this.url).pipe(
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
  postKeywordSearch(keyword: string): Observable<Post[]> {
    return this.http.get<Post[]>(this.url + "/search/" + keyword, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.postKeywordSearch(): error retrieving Keyword Search: ' + err)
        );
      })
    );
  };

  //All Comments for specific posts
  postComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url + "/comments/", this.getHttpOptions()).pipe(
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
    return this.http.post<Post>(this.url, post, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.create(): error creating Post: ' + err)
        );
      })
    );
  };

  createComment(pId: number, comment: Comment):Observable<Comment> {
    return this.http.post<Comment>(this.url + "/" + {pId} + "/comments", comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.createComment(): error creating comment: ' + err)
        );
      })
    );
  };

  update(post: Post, id: number):Observable<Post> {
    post.enabled = true;
    return this.http.put<Post>(this.url + "/" + id, post, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.index(): error retrieving Post: ' + err)
        );
      })
    );
  };

  disable(post: Post, id: number): Observable<void> {
    return this.http.put<void>(this.url + "/disabled/" + id, post, this.getHttpOptions()). pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.disable(): error disabling post: ' + err)
        );
      })
    )
  }

  enable(id: number): Observable<void> {
    return this.http.put<void>(this.url + "/" + id, this.getHttpOptions()). pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.enable(): error enabling post: ' + err)
        );
      })
    )
  }


}
