package com.quiz.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.quiz.constants.ExamConstants;
import com.quiz.ui.ExamLauncher;
import com.quiz.ui.LocateQuestionPaperDialog;
import com.quiz.valueobject.QuestionPaper;

public class MenuActionListener implements ActionListener {

    private ExamLauncher examLauncher;

    public MenuActionListener(ExamLauncher examLauncher) {
        this.examLauncher = examLauncher;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equalsIgnoreCase(ExamConstants.FILE_NEW_EXAM)) {
        	QuestionPaper questionPaper = examLauncher.getQuestionPaper();
        	if (questionPaper == null) {
        		LocateQuestionPaperDialog dialog = new LocateQuestionPaperDialog(examLauncher);
    			dialog.setVisible(true);
        	} else {
        		int showConfirmDialog = JOptionPane.showConfirmDialog(
        				examLauncher, "An Exam is already in progress. Do you want to terminate and start a new exam?");
        		if (showConfirmDialog == 0) {
        			LocateQuestionPaperDialog dialog = new LocateQuestionPaperDialog(examLauncher);
        			dialog.setVisible(true);
        		}
        	}
        } else if (actionCommand.equalsIgnoreCase(ExamConstants.FILE_EXIT)) {
        	int showConfirmDialog = JOptionPane.showConfirmDialog(
    				examLauncher, "Do you wnt to exit from this application?");
        	if (showConfirmDialog == 0) {
        		examLauncher.setVisible(false);
        		System.exit(0);
    		}
        } else if (actionCommand.equalsIgnoreCase(ExamConstants.HELP_ABOUT)) {
            /*AboutDialog about = new AboutDialog(this);
            about.setVisible(true);*/
        }
    }

}
