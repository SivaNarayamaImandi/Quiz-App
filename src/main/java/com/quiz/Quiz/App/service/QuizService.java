package com.quiz.Quiz.App.service;

import com.quiz.Quiz.App.model.Question;
import com.quiz.Quiz.App.model.Quiz;
import com.quiz.Quiz.App.repository.QuestionRepo;
import com.quiz.Quiz.App.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String quizTitle) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numberOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(questions);
        if (quizRepo.save(quiz)!=null){
            try {
                return new ResponseEntity<String>("Questions Added Successful.....", HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>("Questions Added Failed.....", HttpStatus.BAD_REQUEST);
    }
}
