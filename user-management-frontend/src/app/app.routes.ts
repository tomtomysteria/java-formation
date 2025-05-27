import { Routes } from '@angular/router';
import { UserFormComponent } from './components/user-form/user-form.component';

export const routes: Routes = [
  {
    path: 'users/add',
    component: UserFormComponent
  },
  {
    path: 'users/edit/:uuid',
    component: UserFormComponent
  },
  { path: 'users/:uuid', loadComponent: () => import('./components/user-detail/user-detail.component').then(m => m.UserDetailComponent) },
  { path: 'users', loadComponent: () => import('./components/user-list/user-list.component').then(m => m.UserListComponent) },
  { path: '', redirectTo: '/users', pathMatch: 'full' },
];
