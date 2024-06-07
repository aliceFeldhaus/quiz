import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Quiz } from '../../../models/quiz';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getQuizes() {
    return this.http.get<Quiz[]>(`${this.url}/quizes`);
  }

}
