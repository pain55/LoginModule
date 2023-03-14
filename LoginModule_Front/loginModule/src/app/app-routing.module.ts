import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { AdminComponent } from './pages/admin/admin.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { UserComponent } from './pages/user/user.component';

const routes: Routes = [
  {
    path: "",
    component: LoginPageComponent,
    children: [
      {
        path:"login",
        component: LoginComponent
      },
      {
        path:"register",
        component: RegisterComponent
      }
    ]
  },
  {
    path:"admin",
    component:AdminComponent,
    pathMatch: "full"
  },
  {
    path:"user",
    component: UserComponent,
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
