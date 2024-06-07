import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Quiz } from '../../../models/quiz';
import { QuizService } from '../service/quiz.service';

@Component({
  selector: 'app-quiz-list',
  templateUrl: './quiz-list.component.html',
  styleUrl: './quiz-list.component.scss'
})
export class QuizListComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = new MatTableDataSource<Quiz>();
  quizes = new Observable<Quiz[]>();
  // @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private quizService: QuizService){
    
  }
  ngOnInit(): void {
    this.getQuizes();
  }

  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
  }

  getQuizes(){
    this.quizService.getQuizes().subscribe(quizes => {
      this.dataSource = new MatTableDataSource<Quiz>(quizes);
    });
  }
}
