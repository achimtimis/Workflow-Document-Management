import { Routes, RouterModule } from '@angular/router';

import { AlertComponent,AuthGuard,AlertService, AuthenticationService } from '../core/index';
import { UserService } from '../users/index';
import { HomeComponent,CreateComponent,UpdateComponent } from '../pages/admin.page/index';
import { LoginComponent } from '../pages/login/index';
import { RegisterComponent } from '../pages/register/index';

const appRoutes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create', component: CreateComponent },
  { path: 'update/:id', component: UpdateComponent },
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);