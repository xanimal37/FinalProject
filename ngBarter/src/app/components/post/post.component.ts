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

  title: String = "ngTodo";
  selected: Post | null =  null;
  selectedPosts: Post[] | null = null;
  newPost: Post = new Post();
  editPost: Post | null = null;
  posts: Post[] = []
  showEnabled: boolean = false;


  constructor(
    private postService: PostService,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
    ) {
  }

  // ngOnInit(): void {
  //   let postIdString = this.route.snapshot.paramMap.get('id');
  //   if (postIdString) {
  //    console.log('ngOnInit: postId: ' + postIdString);
  //    let postId = ~~postIdString;
  //    console.log(postId);
  //    if (isNaN(postId)) {
  //      this.router.navigateByUrl('invalidId');
  //    }
  //    else {
  //      this.postService.indexAll().subscribe({
  //        next: (post) => {
  //          this.selectedPosts = post;
  //        },
  //        error: (fail) => {
  //          this.router.navigateByUrl('PostNotFound');
  //        }
  //      });
  //    }

  //   }
  //   this.reload();
  //    }

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

}
