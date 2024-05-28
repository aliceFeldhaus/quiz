package br.com.quize.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.quize.model.enums.TruthAnswer;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	private TruthAnswer truth;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Question question;
}
