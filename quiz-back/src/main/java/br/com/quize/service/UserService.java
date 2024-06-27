package br.com.quize.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quize.dto.SaveUserDto;
import br.com.quize.model.User;
import br.com.quize.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public User save(SaveUserDto userDto) {
		User user = new User(userDto.email(), new BCryptPasswordEncoder().encode(userDto.password()));
		return repository.save(user);
	}
	
	public UserDetails getUserByEmail(String email) {
		return repository.findByEmail(email);
	}
}
