import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Auth } from '../../../models/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  authenticate(credenciais: Auth) {
    return this.http.post(`${this.url}/login`, credenciais).pipe(
      map((response: any) => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  logout() {
    localStorage.clear();
  }
}
