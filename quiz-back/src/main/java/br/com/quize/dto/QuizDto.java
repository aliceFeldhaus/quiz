package br.com.quize.dto;

import br.com.quize.model.Quiz;

public record QuizDto(Long id, String name, String description) {

	public QuizDto(Quiz q) {
		this(q.getId(), q.getName(), q.getDescription());
	}

}
