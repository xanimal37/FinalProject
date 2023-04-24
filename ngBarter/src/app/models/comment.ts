import { Post } from "./post";
import { User } from "./user";

export class Comment {
  id: number;
  content: string;
  post: Post | null;
  user: User | null;
  createDate: string;
  updateDate: string;

  constructor(
    id: number = 0,
    content: string = '',
    post: Post | null = null,
    user: User  | null = null,
    createDate: string = '',
    updateDate: string = '',
  ){
    this.id = id;
    this.content = content;
    this.post = post;
    this.user = user;
    this.createDate = createDate;
    this.updateDate = updateDate;

  }

}
