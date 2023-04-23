import { Post } from './../../models/post';
import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit{
// [x: string]: any;

  title: string = "Posts";
  post: Post | null = null;
  selected: Post | null =  null;
  selectedPosts: Post[] | null = null;
  newPost: Post = new Post;
  editPost: Post | null = null;
  posts: Post[] = []
  showEnabled: boolean = false;
  keywordPosts: Post[] | null = null;
  keyword: string = "";
  loggedInUser: User | undefined;



  constructor(
    private postService: PostService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    ) {
    }



    displayPost(post: Post) {
      this.selected = post;
    }

    displayTable():void {
      this.selected = null;
    }

    setEditPost(post: Post): void {
      this.editPost = post;
    }
    cancelEditPost(): void {
      this.editPost = null;
    }

    ngOnInit(): void {
      this.verifyUser();
     }

     verifyUser(): void{
      this.authService.getLoggedInUser().subscribe({
        next: (user: User) => {
          this.loggedInUser = user;
        },
        error: (nojoy) => {
          console.log(nojoy);
        }

       });
      }

     reload() {
      this.postService.indexAll().subscribe({
        next: (postList) => {
          this.posts = postList;
        },
        error: (err) => {
          console.error('Error getting Post Lists:');
          console.error(err);
        }
      });
    }

    createPost(post: Post) {
      this.postService.create(post).subscribe( {
        next: (createdPost) => {
          this.newPost = createdPost;
          this.reload();
        },
        error: (fail) => {
          console.error('Error creating post');
          console.error(fail);
        }
      });

      this.reload();
    }

    updatePost(post: Post, pId: number) {
      this.postService.update(post,pId).subscribe( {
        next: (updatedPost) => {
          this.post = updatedPost;
          this.reload();
        },
        error: (fail) => {
          console.error('Error editing post');
          console.error(fail);
        }
      });
      this.reload();
    }

    postSearch(keyword: string) {
      this.postService.postKeywordSearch(keyword).subscribe( {
        next: (postList) => {
          this.keywordPosts = postList;
          this.reload();
        },
        error: (fail) => {
          console.error('Error retrieving search post');
          console.error(fail);
        }
      });
      this.reload();
    }



    disablePost(id: number) {
      this.postService.disable
    }
}
