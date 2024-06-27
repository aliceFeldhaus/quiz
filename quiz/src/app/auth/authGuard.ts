import { inject } from '@angular/core';
import { AuthService } from '../quiz/service/auth.service';
import { Router } from '@angular/router';

export const authGuard = () => {
  // Injeta manualmente as instancias dos serviços pq não tem construtor para
  // o angular gerencias as dependencias naa construção do componente
  let authService = inject(AuthService);
  let router = inject(Router);

  let token = localStorage.getItem('token');
  if (token) {
    const isExpired = authService.isTokenExpired(token);
    if (isExpired) {
      router.navigate(['/login']);
      return false;
    }
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
