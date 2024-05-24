package br.com.quize.model;

import lombok.Getter;

@Getter
public enum Status {

	ATIVO(0, "Ativo"), INATIVO(1, "Inativo");

	private Integer code;
	private String description;

	Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return getDescription();
	}

}
