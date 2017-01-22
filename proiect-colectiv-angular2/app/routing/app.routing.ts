import { Routes, RouterModule } from '@angular/router';

import { AlertComponent, AuthGuard, AlertService, AuthenticationService } from '../core/index';
import { UserService } from '../users/index';
import {
  LoginComponent, RegisterComponent, HomeComponent, CreateComponent, UpdateComponent,
  ManageComponent, CreateDocumentComponent, ManageDocumentsComponent,
  UpdateDocumentComponent, ManageGroupsComponent, DocumentViewComponent, ManageFluxComponent, ManageZonesComponent
} from '../pages/index';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'createUser', component: CreateComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN'] } },
  { path: 'updateUser/:id', component: UpdateComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN'] } },
  { path: 'manageUsers', component: ManageComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN'] } },
  { path: 'manageGroups', component: ManageGroupsComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN'] } },
  {
    path: 'documents/create', component: CreateDocumentComponent, canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'MANAGER', 'CONTRIBUTOR'] }
  },
  { path: 'zones/manage', component: ManageZonesComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN', 'MANAGER', 'CONTRIBUTOR'] } },
  {
    path: 'documents/manage', component: ManageDocumentsComponent, canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'MANAGER'] }
  },
  { path: 'documents/update/:id', component: UpdateDocumentComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN', 'MANAGER'] } },
  { path: 'documents/mydocuments', component: ManageFluxComponent },
  { path: 'documents/view/:id', component: DocumentViewComponent },
  { path: '**', redirectTo: '/home' }
];

export const routing = RouterModule.forRoot(appRoutes);