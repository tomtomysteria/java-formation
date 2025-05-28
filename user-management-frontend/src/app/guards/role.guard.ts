import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const roles = this.authService.getUserRoles(); // Utilisation de getUserRoles
    if (roles.includes('ADMIN')) {
      return true;
    }
    this.router.navigate(['/login']); // Rediriger si non autorisé
    return false;
  }
}
