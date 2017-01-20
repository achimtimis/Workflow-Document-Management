import { Routes, RouterModule } from '@angular/router';

import { AlertComponent,AuthGuard,AlertService, AuthenticationService } from '../core/index';
import { UserService } from '../users/index';
import { LoginComponent, RegisterComponent, HomeComponent, CreateComponent, UpdateComponent,
          ManageComponent} from '../pages/index';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create', component: CreateComponent },
  { path: 'update/:id', component: UpdateComponent },
  { path: 'manage', component: ManageComponent },
  { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);