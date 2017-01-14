import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './admin_home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { CreateComponent } from './create/index';
import { UpdateComponent } from './update/index'
import { AuthGuard } from './_guards/index';

const appRoutes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create', component: CreateComponent },
  { path: 'update/:id', component: UpdateComponent },
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);