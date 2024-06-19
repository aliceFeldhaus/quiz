import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: 'quizes', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'quizes',
    loadChildren: () => import('./quiz/quiz.module').then((m) => m.QuizModule),
  },
  { path: '**', redirectTo: 'quizes' },
];
