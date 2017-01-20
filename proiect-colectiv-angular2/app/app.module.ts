import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { routing } from './routing/app.routing';

import { AlertComponent,AuthGuard,AlertService, AuthenticationService } from './core/index';
import { UserService } from './users/index';
import { HomeComponent,CreateComponent,UpdateComponent, RegisterComponent, LoginComponent,
          ManageComponent} from './pages/index';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    CreateComponent,
    UpdateComponent,
    ManageComponent
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,

  ],
  bootstrap: [AppComponent]
})

export class AppModule { }