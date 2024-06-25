import { inject } from '@angular/core';
import { AuthService } from '../quiz/service/auth.service';
import { Router } from '@angular/router';

export const authGuard = () => {
  let authService = inject(AuthService);
  let router = inject(Router);

  console.log('passei aqui');
  
  if (localStorage.getItem('token')) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
