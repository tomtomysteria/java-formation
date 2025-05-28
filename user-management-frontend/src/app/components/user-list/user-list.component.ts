import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { getRolesLabels } from '../../utils/role-utils';
import { MainUserHighlightPipe } from '../../pipes/main-user-highlight.pipe';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [RouterModule, CommonModule, MainUserHighlightPipe],
  templateUrl: './user-list.component.html',
})
export class UserListComponent implements OnInit {
  users$!: Observable<User[]>;
  isLoading$!: Observable<boolean>;
  getRolesLabels = getRolesLabels;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.users$ = this.userService.users$;
    this.isLoading$ = this.userService.isLoading$;
    this.userService.getUsers();
  }

  refreshUsers(): void {
    this.userService.getUsers();
  }

  navigateToDetails(userUuid: string): void {
    this.router.navigate(['/users', userUuid]);
  }

  deleteUser(userUuid: string): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
      this.userService.deleteUser(userUuid).subscribe(() => {
        alert('Utilisateur supprimé avec succès');
        this.userService.getUsers();
      });
    }
  }
}
