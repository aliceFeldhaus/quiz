import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, tap } from 'rxjs';
import { Auth } from '../../../models/auth';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
 
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  authenticate(credenciais: Auth) {
    return this.http.post(`${this.url}/login`, credenciais).pipe(
      tap((response: any) => {
        localStorage.setItem('token', response.accessToken);
        localStorage.setItem('refreshToken', response.refreshToken);
      })
    );
  }

  logout() {
    localStorage.clear();
  }

  isTokenExpired(token: string) {
    let decodedToken = jwtDecode(token);
    return (decodedToken.exp || 0) * 1000 < Date.now();
  }

  refreshToken(refreshToken: string) {
    return this.http.post(`${this.url}/login/refresh-token`, refreshToken).pipe(
      tap((response: any) => {
        localStorage.setItem('token', response.accessToken);
      })
    );
  }
}
