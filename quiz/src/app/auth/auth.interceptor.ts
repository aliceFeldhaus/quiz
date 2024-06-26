import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  let token = localStorage.getItem('token');

  // Adiciona o token na requisição enviada ao back-end
  if (token) {
    const cloneReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });
    return next(cloneReq);
  } else {
    return next(req);
  }
};
