import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true, // Rend le composant autonome
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isAuthenticated = false;
  showAuthButton = true;

  constructor(private authService: AuthService, private router: Router) {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      this.showAuthButton = event.url !== '/login';
      this.isAuthenticated = !!this.authService.getToken();
    });
  }

  handleAuthAction(): void {
    if (this.isAuthenticated) {
      this.authService.setToken(''); // Clear the token
      this.router.navigate(['/login']); // Redirect to login page
    } else {
      this.router.navigate(['/login']); // Navigate to login page
    }
  }
}
