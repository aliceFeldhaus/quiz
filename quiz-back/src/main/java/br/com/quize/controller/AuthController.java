package br.com.quize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quize.dto.AutenticacaoDto;
import br.com.quize.model.User;
import br.com.quize.security.AuthenticationData;
import br.com.quize.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody AutenticacaoDto authDto) {
		var userData = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
		// Aqui valida o usuario no banco de dados atraves do usuário e senha
		var authentication = manager.authenticate(userData);
		
		String tokenJwt = tokenService.gerarToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new AuthenticationData(tokenJwt));
	}
}
