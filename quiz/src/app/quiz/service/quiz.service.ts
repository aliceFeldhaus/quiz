import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Quiz } from '../../../models/quiz';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: HttpClient) { }

  getQuizes() {
    return this.http.get<Quiz[]>(`${environment.baseUrlApi}/quizes`);
  }

}
