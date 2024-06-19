import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizSaveComponent } from './quiz-save.component';

describe('QuizSaveComponent', () => {
  let component: QuizSaveComponent;
  let fixture: ComponentFixture<QuizSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QuizSaveComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuizSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
