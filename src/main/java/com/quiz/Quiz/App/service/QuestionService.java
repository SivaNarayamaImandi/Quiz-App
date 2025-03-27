package com.quiz.Quiz.App.service;

import com.quiz.Quiz.App.exception.NewQuestionException;
import com.quiz.Quiz.App.model.Question;
import com.quiz.Quiz.App.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public String addQuestion(Question question) {
        if (questionRepo.save(question) != null)
            return "Question added successfully";
        return new NewQuestionException().getMessage();
    }

    public List<Question> getQuestions() {
        if (!questionRepo.findAll().isEmpty())
            return questionRepo.findAll();
        return null;
    }
}
