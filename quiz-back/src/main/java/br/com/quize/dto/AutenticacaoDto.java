package br.com.quize.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDto(@NotBlank String email, @NotBlank String password) {

}
