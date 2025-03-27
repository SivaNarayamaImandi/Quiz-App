package com.quiz.Quiz.App.exception;

public class NewQuestionException extends RuntimeException {
    @Override
    public String getMessage() {
        return getClass() + " Question not added";
    }
}
