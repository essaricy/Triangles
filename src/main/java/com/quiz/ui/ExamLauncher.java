package com.quiz.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.quiz.exception.QuestionPaperException;
import com.quiz.menu.MenuActionListener;
import com.quiz.menu.MenuLoader;
import com.quiz.valueobject.QuestionPaper;

public class ExamLauncher extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final Dimension APP_DIMENSION = new Dimension(800, 440);

    private QuestionPaper questionPaper;

    public ExamLauncher() {
        setSize(APP_DIMENSION);
        //setLayout(null);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Exam");

        setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setLocation((int) (screenSize.getWidth()/2) - getWidth()/2,
                (int) (screenSize.getHeight()/2) - getHeight()/2);

        new MenuLoader("menu.xml", this, new MenuActionListener(this));

        addComponents();
    }

    private void addComponents() {
        /*try {
            File file = new File("C:\\Documents and Settings\\srikanth.kumar\\Desktop\\QuestionPaper.xls");
            questionPaper = new QuestionPaper(file);
        } catch (QuestionPaperException questionPaperException) {
            questionPaperException.printStackTrace();
        }*/
    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                  ExamLauncher examLauncher = null;
                  try {
                      // Set the look and feel of the application
                      UIManager.setLookAndFeel(
                              UIManager.getSystemLookAndFeelClassName());
                      // launch Exam application
                      examLauncher = new ExamLauncher();
                      examLauncher.setVisible(true);

                      LocateQuestionPaperDialog dialog = new LocateQuestionPaperDialog(examLauncher);
                      dialog.setVisible(true);
                  } catch (ClassNotFoundException classNotFoundException) {
                      System.out.println(classNotFoundException);
                  } catch (InstantiationException instantiationException) {
                      System.out.println(instantiationException);
                  } catch (IllegalAccessException illegalAccessException) {
                      System.out.println(illegalAccessException);
                  } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                      System.out.println(unsupportedLookAndFeelException);
                  }
              }
        });
    }

    public boolean loadQuestionPaper(String fileName) {
        try {
            File file = new File(fileName);
            questionPaper = new QuestionPaper(file);

            int numberOfColumns = 5;
            int numberOfQuestions = questionPaper.getNumberOfQuestions();
            if (numberOfQuestions <= 5 ) {
                numberOfColumns = 5;
            } else if (numberOfQuestions > 50 ) {
                numberOfColumns = 5;
            }
            int numberOfRows = numberOfQuestions/5;
            GridLayout experimentLayout = new GridLayout(numberOfRows, numberOfColumns);
            experimentLayout.setHgap(10);
            experimentLayout.setVgap(10);

            JPanel panel = new JPanel();
            panel.setLayout(experimentLayout);
            JButton questionButton = null;

            int preferredWidth = getWidth()/numberOfColumns;
            int preferredHeight = getHeight()/numberOfRows;
            Dimension preferredSize = new Dimension(preferredWidth, preferredHeight);

            getContentPane().removeAll();
            QuestionListener questionListener = null;
            for (int index = 0; index < numberOfQuestions; index++) {
                questionButton = new JButton("" + (index + 1));
                questionButton.setActionCommand("" + (index + 1));
                questionButton.setPreferredSize(preferredSize);
                questionListener = new QuestionListener(this, questionPaper);
                questionButton.addActionListener(questionListener);
                panel.add(questionButton);
            }
            setContentPane(panel);
            pack();
            return true;
        } catch (QuestionPaperException questionPaperException) {
            JOptionPane.showMessageDialog(this, questionPaperException.getMessage());
        }
        return false;
    }

	public QuestionPaper getQuestionPaper() {
		return questionPaper;
	}

	public void setQuestionPaper(QuestionPaper questionPaper) {
		this.questionPaper = questionPaper;
	}

}
