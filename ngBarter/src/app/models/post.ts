import { Comment } from "./comment";
import { User } from "./user";

export class Post {
  id: number;
  title: string;
  content: string;
  createDate: string;
  updateDate: string;
  enabled: boolean;
  comments: Comment[] | null;
  user: User | null;

  constructor(
    id: number = 0,
    title: string = '',
    content: string = '',
    createDate: string = '',
    updateDate: string = '',
    enabled: boolean = true,
    comments: Comment[] | null,
    user: User  | null
  ){
    this.id = id;
    this.title = title;
    this.content = content;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.enabled = enabled;
    this.comments = comments;
    this.user = user;

  }
}
