import { HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from '../quiz/service/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  let authService = inject(AuthService);
  let token = localStorage.getItem('token');

  // Adiciona o token na requisição enviada ao back-end
  if (token) {
    const cloneReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });
    return next(cloneReq).pipe(
      // Captura caso tenha alguma excecao na requisição
      catchError((error) => {
        // se for do token expirado, solicita um novo accessToken atraves do refreshToken
        const isExpired = authService.isTokenExpired(token as string);
        if(error.status === 401 && isExpired) {
          let refreshToken = localStorage.getItem('refreshToken');
          return handle401Error(req, next, refreshToken as string);
        }
        // se nao for excecao de acesso, lanca o erro
        return throwError(() => { return error });
      })
    );
  } else {
    return next(req);
  }

  function handle401Error(req: HttpRequest<any>, next: HttpHandlerFn, token: string): Observable<HttpEvent<any>> {
    return authService.refreshToken(token).pipe(
        switchMap((response) => {
          // Pega o novo accessToken recebido e reenvia a requisição interceptada
          const cloned = req.clone({
              headers: req.headers.set('Authorization', 'Bearer ' + response.accessToken)
          });
          return next(cloned);
        }),
        // caso der erro na requisição, desloga o usuario do sistema
        catchError(err => {
          authService.logout();
          return throwError(() => { return err });
        })
    );
  }

};

