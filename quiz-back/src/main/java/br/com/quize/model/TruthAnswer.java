package br.com.quize.model;

import lombok.Getter;

@Getter
public enum TruthAnswer {

	TRUTH(0, "Verdadeiro"),
	FALSE(1, "Falso");

	private Integer code;
	private String description;
	
	TruthAnswer(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return getDescription();
	}
}
