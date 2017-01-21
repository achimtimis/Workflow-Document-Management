import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { routing } from './routing/app.routing';

import { AlertComponent, AuthGuard, AlertService, AuthenticationService } from './core/index';
import { UserService } from './users/index';
import { DocumentService } from './documents/index';
import {
  HomeComponent, CreateComponent, UpdateComponent, RegisterComponent, LoginComponent,
  ManageComponent, ManagerHomeComponent, CreateDocumentComponent,ManageDocumentsComponent
} from './pages/index';

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
    ManageComponent,
    ManagerHomeComponent,
    CreateDocumentComponent,
    ManageDocumentsComponent,
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    DocumentService

  ],
  bootstrap: [AppComponent]
})

export class AppModule { }