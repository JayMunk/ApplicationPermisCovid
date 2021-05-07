import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateChildComponent } from './components/create-child/create-child.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { Error404Component } from './components/error404/error404.component';
import { GetPermisComponent } from './components/get-permis/get-permis.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RenewPermisComponent } from './components/renew-permis/renew-permis.component';
import { RequestPermisComponent } from './components/request-permis/request-permis.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'dashboard', component: DashboardComponent },//, canActivate: [AuthGuard] },
  { path: 'getPermis', component: GetPermisComponent },//, canActivate: [AuthGuard] },
  { path: 'renewPermis', component: RenewPermisComponent },//, canActivate: [AuthGuard] },
  { path: 'requestPermis', component: RequestPermisComponent },//, canActivate: [AuthGuard] },
  { path: 'createUser', component: CreateUserComponent },
  { path: 'createChild', component: CreateChildComponent },//, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: Error404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
