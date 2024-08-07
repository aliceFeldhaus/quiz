package br.com.quize.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private JwtAuthenticationEntryPoint authEntryPoint;
	@Autowired
	private SecurityAuthenticationFilter authFilter;
	
	private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfigurations(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }
	
	// Desabilita o processo de autenticacao STATEFULL (via sessão do sistema) e permite que a autenticação seja personalizada
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors -> cors.configurationSource(corsConfigurationSource))
				.csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // nao guarda o status de autenticacao por sessao, toda nova requisicao deve autenticar novamente o usuario
	            .and().authorizeHttpRequests(
	            		authorizeHttpRequests -> {
	            			authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/login").permitAll(); // permite requisicao sem autenticacao ao endpoint /login
	            			authorizeHttpRequests .requestMatchers(HttpMethod.POST, "/login/refresh-token").permitAll();
	            			authorizeHttpRequests .requestMatchers(HttpMethod.POST, "/users").permitAll() ;
	            			authorizeHttpRequests .anyRequest().authenticated(); // qualquer outra requisicao deve ser autenticada
	            		})
	            .exceptionHandling().authenticationEntryPoint(authEntryPoint) // Faz retornar status 401 em caso de excecao na autenticação
	            .and().addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // seta a ordem de execucao dos filtros, primeiro o filtro personalizado e depois o filtro padrao do spring
	            .build();
	}
	
	// utilizado pelo spring para injecao de dependencia da classe AuthenticationManager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// informa ao Spring qual o formato de criptografia da senha
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
