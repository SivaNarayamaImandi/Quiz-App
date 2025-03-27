package com.quiz.Quiz.App.repository;

import com.quiz.Quiz.App.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    List<Question> findByDifficultyLevelAndCategory(String difficultyLevel, String category);

    List<Question> findByDifficultyLevel(String difficultyLevel);
}
