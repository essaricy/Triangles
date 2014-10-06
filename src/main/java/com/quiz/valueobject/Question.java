package com.quiz.valueobject;

import java.util.List;

public class Question {
	public static final int UNANSWERED = 0;
	public static final int ANSWERED_CORRECT = 1;
	public static final int ANSWERED_WRONG = 2;

	private int questionNumber;
	private String question;
	private int answerCode;
	private String answer;
	private List options;
	private int state;

	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getAnswerCode() {
		return answerCode;
	}
	public void setAnswerCode(int answerCode) {
		this.answerCode = answerCode;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List getOptions() {
		return options;
	}
	public void setOptions(List options) {
		this.options = options;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String toString() {
		return super.toString() + "(questionNumber = " + questionNumber + ", "
			+ "question = " + question + ", "
			+ "answerCode = " + answerCode + ", "
			+ "answer = " + answer + ", "
			+ "options = " + options + ", "
			+ "state = " + state + ") \n";
	}

}
