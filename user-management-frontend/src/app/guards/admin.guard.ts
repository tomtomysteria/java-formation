import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const token = this.authService.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1])); // Décoder le JWT
      if (payload.roles && payload.roles.includes('ADMIN')) {
        return true;
      }
    }
    this.router.navigate(['/login']); // Rediriger si non autorisé
    return false;
  }
}
