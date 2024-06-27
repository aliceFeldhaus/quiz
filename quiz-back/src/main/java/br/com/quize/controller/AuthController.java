package br.com.quize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quize.dto.AutenticacaoDto;
import br.com.quize.model.User;
import br.com.quize.security.AuthenticationData;
import br.com.quize.security.TokenService;
import br.com.quize.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody AutenticacaoDto authDto) {
		var userData = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
		// Aqui valida o usuario no banco de dados atraves do usuário e senha
		var authentication = manager.authenticate(userData);
		
		String tokenJwt = tokenService.gerarAccessToken((User) authentication.getPrincipal());
		String refreshTokenJwt = tokenService.gerarRefreshToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new AuthenticationData(tokenJwt, refreshTokenJwt));
	}
	

	@PostMapping(path = "/refresh-token")
	public ResponseEntity<?> getAccesTokenByRefreshToken(@Valid @RequestBody String refreshToken) {
		// Se não lançar nenhuma excecao é pq o token é valido, caso lance o mapper tratará
		String userInToken = tokenService.validateRefreshToken(refreshToken);

		UserDetails userByEmail = userService.getUserByEmail(userInToken);
		String accessToken = tokenService.gerarAccessToken((User) userByEmail);
		String newRefreshToken = tokenService.gerarRefreshToken((User) userByEmail);
		return ResponseEntity.ok(new AuthenticationData(accessToken, newRefreshToken));
	}
}
