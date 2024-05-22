package br.com.quize.dto;

import java.util.List;

import br.com.quize.model.Question;
import br.com.quize.model.Quiz;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarQuizDto {

		private Long id;
		@NotBlank(message = "{nome.obrigatorio}")
		private String name;
		@NotBlank(message = "{descricao.obrigatorio}")
		private String description;
		private List<Question> questions;
		
		public void convertFrom(Quiz quiz) {
			this.id = quiz.getId();
			this.name = quiz.getName();
			this.description = quiz.getDescription();
			this.questions = quiz.getQuestions();
		}
}
