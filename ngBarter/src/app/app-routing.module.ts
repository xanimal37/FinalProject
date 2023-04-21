import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasklistComponent } from './components/tasklist/tasklist.component';
import { HomeComponent } from './components/home/home.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { PostComponent } from './components/post/post.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'tasks',component: TasklistComponent},
  { path: 'useraccount',component: UserAccountComponent},
  { path: 'posts',component: PostComponent},
  { path: 'notifications',component: NotificationComponent},
  { path: 'complaints',component: ComplaintComponent},
  { path: 'admin',component: AdminComponent},
  {path: 'tasks',component: TasklistComponent},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'usersList', component: UserListComponent },

  //goes at the end
  {path:'**',component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
