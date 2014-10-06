package com.quiz.valueobject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.quiz.exception.QuestionPaperException;
import com.quiz.util.FileUtil;
import com.quiz.util.QuestionPaperReaderUtil;

public class QuestionPaper {

    public static final String QUESTION_PAPER_FILE_EXTENSION = "xls";

    private File questionPaper;

    private int numberOfQuestions;

    private List questionsList;

    public QuestionPaper(File questionPaper) throws QuestionPaperException {
        this.questionPaper = questionPaper;
        loadQuestionPaper();
    }

    private void loadQuestionPaper() throws QuestionPaperException {
        if (questionPaper == null || !questionPaper.exists()) {
            throw new QuestionPaperException("Question Paper does not exist.");
        }
        if (!questionPaper.isFile()) {
            throw new QuestionPaperException("Question Paper must be a file.");
        }
        String extension = FileUtil.getExtension(questionPaper);
        if (extension == null
                || !extension.equalsIgnoreCase(QUESTION_PAPER_FILE_EXTENSION)) {
            throw new QuestionPaperException(
                    "Question Paper only in Microsoft Excel (.xls) format is supported.");
        }
        questionsList = QuestionPaperReaderUtil.getQuestions(questionPaper);
        if (questionsList == null || questionsList.isEmpty()) {
            throw new QuestionPaperException(
                    "Not a single question found in the Question Paper.");
        }
        numberOfQuestions = questionsList.size();
        System.out.println("questionsList " + questionsList);
    }

    public File getQuestionPaper() {
        return questionPaper;
    }

    public void setQuestionPaper(File questionPaper) {
        this.questionPaper = questionPaper;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public List getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List questionsList) {
        this.questionsList = questionsList;
    }

    public static void main(String[] args) {
        try {
            File file = new File(
                    "C:\\Documents and Settings\\srikanth.kumar\\Desktop\\QuestionPaper.xls");
            QuestionPaper questionPaper = new QuestionPaper(file);
            int size = questionPaper.getQuestionsList().size();

            Question question = null;
            for (int index = 0; index < size; index++) {
                question = questionPaper.getNextRandomQuestion();
                System.out.println("Asking question " + question);
                if (question == null) {
                    System.out.println("All questions are over.");
                    break;
                } else {
                    question.setState(Question.ANSWERED_CORRECT);
                }
            }
            System.out.println(" question ==> "
                    + questionPaper.getQuestionsList());
        } catch (QuestionPaperException questionPaperException) {
            questionPaperException.printStackTrace();
        }
    }

    private Question getNextRandomQuestion() {
        int rawRandomNumber;
        int min = 0;
        int max = questionsList.size() - 1;
        String questionString = null;

        List askedQuestions = new ArrayList();
        Question question = null;

        do {
            rawRandomNumber = (int) (Math.random() * (max - min + 1)) + min;
            question = (Question) questionsList.get(rawRandomNumber);
            if (question != null) {
                questionString = question.getQuestion().toUpperCase();
                if (question.getState() == Question.UNANSWERED) {
                    break;
                }
                // This question is already picked up by random
                if (!askedQuestions.contains(questionString)) {
                    askedQuestions.add(questionString);
                }
                // all questions are asked
                if (askedQuestions.size() == questionsList.size()) {
                    break;
                }
            }

        } while (true);

        return question;
    }

    public Question getQuestion(int questionNumber) {
        Question question = null;
        int size = questionsList.size();
        for (int index = 0; index < size; index++) {
            question = (Question) questionsList.get(index);
            if (question != null) {
                if (questionNumber == question.getQuestionNumber()) {
                    break;
                }
            }
        }
        return question;
    }
}
