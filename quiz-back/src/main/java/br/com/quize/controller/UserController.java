package br.com.quize.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quize.dto.SaveUserDto;
import br.com.quize.model.User;
import br.com.quize.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<User> save(@Valid @RequestBody SaveUserDto userDto) {
			return ResponseEntity.ok(service.save(userDto));
	}
	
}
