import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { Error404Component } from './components/error404/error404.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RenewPermisComponent } from './components/renew-permis/renew-permis.component';
import { LogoutComponent } from './components/logout/logout.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { GetPermisComponent } from './components/get-permis/get-permis.component';
import { RequestPermisComponent } from './components/request-permis/request-permis.component';
import { CreateChildComponent } from './components/create-child/create-child.component';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Error404Component,
    RenewPermisComponent,
    LogoutComponent,
    CreateUserComponent,
    DashboardComponent,
    GetPermisComponent,
    RequestPermisComponent,
    CreateChildComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
