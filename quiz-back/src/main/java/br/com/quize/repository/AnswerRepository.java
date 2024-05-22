package br.com.quize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quize.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
