import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';

import { RegisterComponent } from './components/register/register.component';

import { TasklistComponent } from './components/tasklist/tasklist.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    TasklistComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
