package br.com.quize.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quize.dto.CadastrarQuizDto;
import br.com.quize.dto.DetalheQuizDto;
import br.com.quize.dto.QuizDto;
import br.com.quize.service.QuizService;

@RestController
@RequestMapping("/quizes")
public class QuizController {

	QuizService service;

	public QuizController(QuizService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<QuizDto>> listaTodosQuiz() {
		return ResponseEntity.ok(service.listAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DetalheQuizDto> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody CadastrarQuizDto quizDto) {
		try {
			service.save(quizDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody CadastrarQuizDto quizDto) {
		try {
			service.save(quizDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
