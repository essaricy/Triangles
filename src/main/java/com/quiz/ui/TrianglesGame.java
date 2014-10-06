package com.quiz.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.intelligence.games.triangle.ImagePanel;
import com.intelligence.games.triangle.TriangleDetail;
import com.quiz.exception.QuestionPaperException;
import com.quiz.valueobject.Question;
import com.quiz.valueobject.QuestionPaper;

public class TrianglesGame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private static final Dimension APP_DIMENSION = new Dimension(860,600);

    private boolean questionOn;

    private JPanel imagePanel;

    private QuestionPanel questionPanel;

    private QuestionPaper questionPaper;

    public TrianglesGame() {
        try {
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            File imageFile = new File("D:\\projects\\Quiz\\src\\main\\resources\\sierpinski.png");
            BufferedImage image = ImageIO.read(imageFile);

            setSize((int) APP_DIMENSION.getWidth(), image.getHeight() + 50);

            imagePanel = new ImagePanel(image, this);
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            imagePanel.setBounds(5, 5, image.getWidth(), image.getHeight());
            add(imagePanel);

            questionPaper = new QuestionPaper(new File("C:\\Users\\Toshiba\\Desktop\\Quiz\\QuestionPaper.xls"));
            Question question = new Question();
            question.setQuestion("This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.");
            List options = new ArrayList();
            options.add("Option 1");
            options.add("Option 2");
            options.add("Option 3");
            options.add("Option 4");
            options.add("Option 5");
            question.setOptions(options);

            Dimension QUESTION_PANEL_DIMENSION = new Dimension(
                    getWidth() - image.getWidth() - 30, image.getHeight());
            questionPanel = new QuestionPanel(question, QUESTION_PANEL_DIMENSION);
            questionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            questionPanel.setBounds(image.getWidth() + 10, 5,
                    (int) QUESTION_PANEL_DIMENSION.getWidth(), (int) QUESTION_PANEL_DIMENSION.getHeight());
            add(questionPanel);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (QuestionPaperException questionPaperException) {
            questionPaperException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TrianglesGame game = new TrianglesGame();
        game.setVisible(true);
    }

    public void setQuestion(TriangleDetail detail) {
        questionPanel.setQuestion(questionPaper.getQuestion(detail.getTriangleNumber()));
        questionOn = true;
    }

    public boolean isQuestionOn() {
        return questionOn;
    }

    public void setQuestionOn(boolean questionOn) {
        this.questionOn = questionOn;
    }

    public void animateQuestionPanel() {
        try {
            for (int index = 0; index < 3; index++) {
                questionPanel.setBackground(Color.RED);
                Thread.sleep(100);
                questionPanel.setBackground(Color.GRAY);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
