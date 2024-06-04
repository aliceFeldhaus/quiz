package br.com.quize.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> tratarErro500(NullPointerException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratarErro404(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
	    var erros = ex.getFieldErrors();
	    return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}

	private record DadosErroValidacao(String campo, String mensagem) {
	    public DadosErroValidacao(FieldError erro) {
	        this(erro.getField(), erro.getDefaultMessage());
	    }
	}
}
