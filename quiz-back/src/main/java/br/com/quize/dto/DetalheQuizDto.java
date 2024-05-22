package br.com.quize.dto;

import java.util.List;

import br.com.quize.model.Question;
import br.com.quize.model.Quiz;
import br.com.quize.model.Status;
import br.com.quize.model.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalheQuizDto {

	private Long id;
	private String name;
	private String description;
	private Status status;
	private Visibility visibility;
	private List<Question> questions;
	
	public void convertFrom(Quiz quiz) {
		this.id = quiz.getId();
		this.name = quiz.getName();
		this.description = quiz.getDescription();
		this.questions = quiz.getQuestions();
		this.status = quiz.getStatus();
		this.visibility = quiz.getVisibility();
	}
}
