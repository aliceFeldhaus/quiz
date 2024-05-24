package br.com.quize.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.quize.dto.CadastrarQuizDto;
import br.com.quize.dto.DetalheQuizDto;
import br.com.quize.dto.QuizDto;
import br.com.quize.model.Question;
import br.com.quize.model.Quiz;
import br.com.quize.model.Status;
import br.com.quize.model.Visibility;
import br.com.quize.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizService {

	QuizRepository repository;

	public QuizService(QuizRepository repository) {
		this.repository = repository;
	}

	public List<QuizDto> listAll() {
		List<Quiz> findAll = repository.findAll();
		return findAll.stream().map(q -> new QuizDto(q)).collect(Collectors.toList());
	}

	public DetalheQuizDto getById(Long id) {
		Quiz quiz = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz não encontrado!"));
		DetalheQuizDto quizDto = new DetalheQuizDto();
		quizDto.convertFrom(quiz);
		return quizDto;
	}

	public QuizDto save(CadastrarQuizDto quizDto) {
		Quiz quiz = new Quiz(quizDto.getId(), quizDto.getName(), quizDto.getDescription());
		quiz.setStatus(Status.ATIVO);
		quiz.setVisibility(Visibility.PUBLIC);
		quiz.setQuestions(quizDto.getQuestions());
		atualizaDadosCascata(quiz.getQuestions(), quiz);
		Quiz quizSaved = repository.save(quiz);
		return new QuizDto(quizSaved);
	}

	public QuizDto update(Long id, CadastrarQuizDto quizDto) {
		Quiz quiz = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz não encontrado!"));
		quiz = new Quiz(quizDto.getId(), quizDto.getName(), quizDto.getDescription());
		quiz.setQuestions(quizDto.getQuestions());
		atualizaDadosCascata(quiz.getQuestions(), quiz);
		Quiz quizSaved = repository.save(quiz);
		return new QuizDto(quizSaved);
	}

	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quiz não encontrado!"));
		repository.deleteById(id);
	}
	
	private void atualizaDadosCascata(List<Question> questions, Quiz quiz) {
		for (Question question : questions) {
			question.setQuiz(quiz);
			if(question.getAnswers() != null) {
				question.getAnswers().forEach(answer -> answer.setQuestion(question));
			}
		}
	}
}
