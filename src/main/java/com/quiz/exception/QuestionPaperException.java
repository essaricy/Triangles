package com.quiz.exception;

public class QuestionPaperException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuestionPaperException(String message) {
		super(message);
	}

	public QuestionPaperException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
