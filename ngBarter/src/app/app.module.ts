import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PostComponent } from './components/post/post.component';
import { UserAccountComponent } from './components/user-account/user-account.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { NotificationComponent } from './components/notification/notification.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { DatePipe } from '@angular/common';
import { TasklistComponent } from './components/tasklist/tasklist.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { OtherUserProfileComponent } from './components/other-user-profile/other-user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { TaskbyskillPipe } from './pipes/taskbyskill.pipe';
import { AcceptedTaskComponent } from './components/accepted-task/accepted-task.component';
import { TaskdashComponent } from './components/taskdash/taskdash.component';
import { NewtaskComponent } from './components/newtask/newtask.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NewTaskMessageComponent } from './components/new-task-message/new-task-message.component';


@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    UserAccountComponent,
    PageNotFoundComponent,
    ComplaintComponent,
    NotificationComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    TasklistComponent,
    HomeComponent,
    AdminComponent,
    NavbarComponent,
    OtherUserProfileComponent,
    UserListComponent,
    TaskbyskillPipe,
    AcceptedTaskComponent,
    TaskdashComponent,
    NewtaskComponent,
    NewTaskMessageComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [ DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
