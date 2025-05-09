package com.quiz.Quiz.App.service;

import com.quiz.Quiz.App.model.Question;
import com.quiz.Quiz.App.model.QuestionWrapper;
import com.quiz.Quiz.App.model.Quiz;
import com.quiz.Quiz.App.model.Response;
import com.quiz.Quiz.App.repository.QuestionRepo;
import com.quiz.Quiz.App.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (quizRepo.save(quiz) != null) {
            try {
                return new ResponseEntity<String>("Questions Added Successful.....", HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>("Questions Added Failed.....", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
//        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
//        for (Question question : questionsFromDB) {
//            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestion(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
//            questionsForUser.add(questionWrapper);
//        }
        for (Question question : quiz.get().getQuestions()) {
            questionsForUser.add(new QuestionWrapper(question.getId(), question.getQuestion(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
        }
        if (!questionsForUser.isEmpty()) {
            try {
                return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
        List<Question> quiz = quizRepo.findById(id).get().getQuestions();
        int score = 0;
        int i = 0;
        if(!response.isEmpty())
        {
            for (Response res : response) {
                if (res.getResponse().equals(quiz.get(i).getRightAnswer())) {
                    score++;
                }
                i++;
            }
            try {
                return new ResponseEntity<Integer>(score, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Integer>(score, HttpStatus.NOT_FOUND);
    }
}
