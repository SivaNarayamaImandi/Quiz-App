package com.quiz.Quiz.App.controller;

import com.quiz.Quiz.App.model.Question;
import com.quiz.Quiz.App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("add-question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        if (questionService.addQuestion(question) != null)
            return new ResponseEntity<String>(questionService.addQuestion(question), HttpStatus.OK);
        return new ResponseEntity<String>(questionService.addQuestion(question), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("get-questions")
    public ResponseEntity<List<Question>> getQuestions() {
        if (questionService.getQuestions()!=null)
            return new ResponseEntity<List<Question>>(questionService.getQuestions(), HttpStatus.OK);
        return new ResponseEntity<List<Question>>(questionService.getQuestions(), HttpStatus.NOT_FOUND);
    }
}
