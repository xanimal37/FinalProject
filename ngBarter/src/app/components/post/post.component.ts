import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
[x: string]: any;

  title: string = "Posts";
  selected: Post | null =  null;
  selectedPosts: Post[] | null = null;
  newPost: Post = new Post;
  editPost: Post | null = null;
  posts: Post[] = []
  showEnabled: boolean = false;
  keywordPosts: Post[] | null = null;
  keyword: string = "";



  constructor(
    private postService: PostService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
    ) {
    }



    displayPost(post: Post) {
      this.selected = post;
    }

    displayTable():void {
      this.selected = null;
    }

    setEditPost(): void {
      this.editPost = Object.assign({}, this.selected);
    }
    cancelEditPost(): void {
      this.editPost = null;
    }

    ngOnInit(): void {
      this.reload();
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
          this.newPost = new Post();
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
          this.editPost = new Post();
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
