package com.quiz.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.quiz.valueobject.Question;
import com.quiz.valueobject.QuestionPaper;

public class QuestionListener implements ActionListener {

    private ExamLauncher examLauncher;

    private QuestionPaper questionPaper;

    private List askedQuestions;

    public QuestionListener(ExamLauncher examLauncher, QuestionPaper questionPaper) {
        this.examLauncher = examLauncher;
        this.questionPaper = questionPaper;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = null;
        Object source = actionEvent.getSource();
        try {
            if (source instanceof JButton) {
                button = (JButton) source;
                String actionCommand = actionEvent.getActionCommand();
                System.out.println("actionCommand " + actionCommand);
                if (actionCommand != null) {
                    int questionNumber = Integer.parseInt(actionCommand);
                    Question question = questionPaper.getQuestion(questionNumber);
                    // Launch question
                    QuestionDialog questionDialog = new QuestionDialog(examLauncher, question);
                    questionDialog.setVisible(true);
                    // Check answer and update question object.
                    String selectedAnswer = questionDialog.getSelectedAnswer();
                    if (selectedAnswer != null) {
                    	if (askedQuestions == null) {
                    		askedQuestions = new ArrayList();
                    	}
                        if (selectedAnswer.equalsIgnoreCase(question.getAnswer())) {
                            // say Correct answer
                            question.setState(Question.ANSWERED_CORRECT);
                            //button.setEnabled(false);
                            button.setBackground(Color.GREEN);
                            //Icon icon = new ImageIcon("D:\\projects\\Quiz\\src\\main\\resources\\glossy_up.png");
                            Icon icon = new ImageIcon("D:\\projects\\Quiz\\src\\main\\resources\\emoticon-animal-016.gif");
                            button.setIcon(icon);
                            button.removeActionListener(this);
                        } else {
                            // say wrong answer
                            question.setState(Question.ANSWERED_WRONG);
                            //button.setEnabled(false);
                            button.setBackground(Color.RED);
                            Icon icon = new ImageIcon("D:\\projects\\Quiz\\src\\main\\resources\\glossy_down.png");
                            button.setIcon(icon);
                            button.removeActionListener(this);
                        }
                    } else {
                        // No answer
                        question.setState(Question.UNANSWERED);
                    }
                }
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException);
        }
    }

}
