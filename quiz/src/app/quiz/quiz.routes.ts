import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuizListComponent } from './quiz-list/quiz-list.component';
import { QuizSaveComponent } from './quiz-save/quiz-save.component';

const routes: Routes = [
  { path: '', component: QuizListComponent },
  { path: 'cadastrar', component: QuizSaveComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class QuizRouteModule {}
