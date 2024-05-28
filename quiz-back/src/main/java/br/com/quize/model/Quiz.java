package br.com.quize.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.quize.model.enums.Status;
import br.com.quize.model.enums.Visibility;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Enumerated(EnumType.ORDINAL)
	private Visibility visibility;

	@OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz")
	private List<Question> questions;
	
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;

//	private String image;
	
	public Quiz(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
