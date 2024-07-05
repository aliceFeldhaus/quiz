package br.com.quize.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SaveUserDto(@NotBlank @Email String email, @NotBlank String password) {

}
