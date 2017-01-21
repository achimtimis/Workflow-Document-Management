import { Routes, RouterModule } from '@angular/router';

import { AlertComponent,AuthGuard,AlertService, AuthenticationService } from '../core/index';
import { UserService } from '../users/index';
import { LoginComponent, RegisterComponent, HomeComponent, CreateComponent, UpdateComponent,
          ManageComponent,ManagerHomeComponent} from '../pages/index';

const appRoutes: Routes = [
  { path: 'admin/home', component: HomeComponent, canActivate: [AuthGuard], data: {roles:['ADMIN']} },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'admin/create', component: CreateComponent, canActivate: [AuthGuard], data: {roles:['ADMIN']} },
  { path: 'admin/update/:id', component: UpdateComponent, canActivate: [AuthGuard], data: {roles:['ADMIN']} },
  { path: 'admin/manage', component: ManageComponent, canActivate: [AuthGuard], data: {roles:['ADMIN']} },
  { path: 'manager/home', component: ManagerHomeComponent, canActivate: [AuthGuard], data: {roles:['MANAGER']}},
  { path: '**', redirectTo: 'login' }
];

export const routing = RouterModule.forRoot(appRoutes);