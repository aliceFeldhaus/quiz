import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuizListComponent } from './quiz-list/quiz-list.component';
import { SharedModule } from '../../shared/shared.module';
import { QuizRouteModule } from './quiz.routes';

@NgModule({
  declarations: [
    QuizListComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    QuizRouteModule
  ]
})
export class QuizModule { }
