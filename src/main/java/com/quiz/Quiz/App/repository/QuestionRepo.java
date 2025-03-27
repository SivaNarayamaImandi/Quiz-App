package com.quiz.Quiz.App.repository;

import com.quiz.Quiz.App.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
