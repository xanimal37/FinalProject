import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { PostComponent } from './components/post/post.component';
import { UserAccountComponent } from './components/user-account/user-account.component';

const routes: Routes = [
   { path: 'userAccount', component: UserAccountComponent },
   { path: 'post', component: PostComponent},
   { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
