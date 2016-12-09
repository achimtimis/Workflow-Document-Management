import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule}   from '@angular/router';

import {AppComponent}  from './app.component';
import {LoginFormComponent} from './forms/login-form.component';
import {RegisterFormComponent} from './forms/register-form.component';

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {
        path: 'register',
        component: RegisterFormComponent
      },
      {
        path: 'login',
        component: LoginFormComponent
      },
    ])
  ],
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent
  ],
  bootstrap: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent
  ]
})
export class AppModule {
}
