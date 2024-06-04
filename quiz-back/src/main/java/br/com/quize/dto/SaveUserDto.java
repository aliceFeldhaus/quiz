package br.com.quize.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveUserDto(@NotBlank String email, @NotBlank String password) {

}
