import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-form',
  standalone: true, // Rend le composant autonome
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class UserFormComponent implements OnInit {
  userForm!: FormGroup;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute // Inject ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      uuid: [null],
      username: ['', [Validators.required]],
      roles: ['', [Validators.required]],
      password: [''] // Initialize without validators
    });

    const userUuid = this.route.snapshot.paramMap.get('uuid'); // Get the UUID from the URL
    if (userUuid) {
      this.isEditMode = true;
      this.userService.getUserByUuid(userUuid).subscribe((user: User) => {
        const rolesAsString = user.roles.map(role => role.name).join(', ');
        this.userForm.patchValue({ ...user, roles: rolesAsString }); // Prefill the form with the user's data
      });
    }

    // Dynamically update password validators based on edit mode
    if (!this.isEditMode) {
      this.userForm.get('password')?.setValidators([Validators.required]);
    } else {
      this.userForm.get('password')?.clearValidators();
    }
    this.userForm.get('password')?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      const rolesArray = formValue.roles.split(',').map((roleName: string) => roleName.trim()); // Convert roles to an array of strings

      const user: User = {
        ...formValue,
        roles: rolesArray,
        password: formValue.password || undefined // Ensure password is included even if empty
      };

      if (this.isEditMode) {
        this.userService.updateUser(user).subscribe({
          next: () => {
          alert('Utilisateur mis à jour avec succès');
          this.router.navigate(['/users']);
          },
          error: (err) => {
            alert(`Erreur lors de la mise à jour de l'utilisateur : ${err.error?.message || err.message || 'Une erreur est survenue.'}`);
          }
        });
      } else {
        this.userService.addUser(user).subscribe({
          next: () => {
            alert('Utilisateur ajouté avec succès');
            this.router.navigate(['/users']);
          },
          error: (err) => {
            alert(`Erreur lors de l'ajout de l'utilisateur : ${err.error?.message || err.message || 'Une erreur est survenue.'}`);
          }
        });
      }
    }
  }
}
