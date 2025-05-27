import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { catchError, finalize, tap } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8085/users';
  private usersSubject = new BehaviorSubject<User[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  users$ = this.usersSubject.asObservable();
  isLoading$ = this.loadingSubject.asObservable();

  constructor(private http: HttpClient) {}

  getUsers(): void {
    this.loadingSubject.next(true);
    this.http.get<User[]>(this.apiUrl)
      .pipe(
        tap(users => this.usersSubject.next(users)),
        catchError(error => {
          console.error('Erreur lors du chargement des utilisateurs', error);
          return [];
        }),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe();
  }

  getUserByUuid(userUuid: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userUuid}`);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${user.uuid}`, user);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}`, user);
  }

  deleteUser(userUuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userUuid}`);
  }
}
