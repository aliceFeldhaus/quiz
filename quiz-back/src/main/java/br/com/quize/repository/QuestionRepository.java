package br.com.quize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quize.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
