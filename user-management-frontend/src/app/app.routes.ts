import { Routes } from '@angular/router';
import { AdminGuard } from './guards/admin.guard';

export const routes: Routes = [
  {
    path: 'users',
    canActivate: [AdminGuard],
    children: [
      { path: 'add', loadComponent: () => import('./components/user-form/user-form.component').then(m => m.UserFormComponent) },
      { path: 'edit/:uuid', loadComponent: () => import('./components/user-form/user-form.component').then(m => m.UserFormComponent)},
      { path: ':uuid', loadComponent: () => import('./components/user-detail/user-detail.component').then(m => m.UserDetailComponent) },
      { path: '', loadComponent: () => import('./components/user-list/user-list.component').then(m => m.UserListComponent) },
    ],
  },
  { path: 'login', loadComponent: () => import ('./components/login/login.component').then(m => m.LoginComponent) },
  { path: '', redirectTo: '/users', pathMatch: 'full' },
];
