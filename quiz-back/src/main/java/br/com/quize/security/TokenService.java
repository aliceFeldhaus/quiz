package br.com.quize.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.quize.model.User;

@Service
public class TokenService {
	
	private static final String ISSUER = "API quize";
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(User usuario) {
		try {
			var algoritimo = Algorithm.HMAC256(secret);
			String token = JWT.create()
							.withIssuer(ISSUER)	// nome da aplicação geradora do token
							.withSubject(usuario.getEmail())
							.withClaim("id", usuario.getId()) // para salvar qq informação extra que quiser
							.withExpiresAt(dataExpiracao())
							.sign(algoritimo);
			return token;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar o token JWT", e);
		}
	}
	
	public String validateToken(String tokenJWT) {
		try {
			var algoritimo = Algorithm.HMAC256(secret);
			return JWT.require(algoritimo)
							.withIssuer(ISSUER)	// nome da aplicação geradora do token
							.build()
							.verify(tokenJWT)	// valida o token
							.getSubject();
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao validar o token JWT", e);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
