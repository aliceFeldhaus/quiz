package br.com.quize.security;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().append(json(request.getRequestURI()));
    }
    
	private CharSequence json(String path) {
		LocalDate date = LocalDate.now();
		return "{"
				+ "\"timestamp\": " + date + ", " 
				+ "\"status\": 401, "
				+ "\"error\": \"Unauthorized\", "
				+ "\"message\": \"Invalid token!\", "
				+ "\"path\": \""+ path +"}";
	}
}
