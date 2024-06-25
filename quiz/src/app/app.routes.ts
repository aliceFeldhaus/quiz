import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from './auth/authGuard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: HomeComponent,
    children: [
      { path: '', redirectTo: 'quizes', pathMatch: 'full' },
      {
        path: 'quizes',
        loadChildren: () =>
          import('./quiz/quiz.module').then((m) => m.QuizModule),
      },
    ],
    canActivate: [authGuard]
  },
  { path: '**', redirectTo: 'quizes' },
];
