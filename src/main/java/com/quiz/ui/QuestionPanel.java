package com.quiz.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.quiz.valueobject.Question;
import com.quiz.valueobject.QuestionPaper;

public class QuestionPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private String selectedAnswer;

    private Question question;

    private JTextArea questionArea;

    private JPanel optionsPanel;

    private JButton OK;

    private ButtonGroup buttonGroup;

    private String[] ACTION_COMMANDS = {"A", "B", "C", "D", "E"};

    private Dimension dimension;

    private QuestionPaper questionPaper;

    private JPanel questionPanel;

    public QuestionPanel(Question question, Dimension dimension) {
        this.question = question;
        this.dimension = dimension;
        setSize(dimension);
        setBackground(Color.WHITE);
        setLayout(null);
        
        addComponents();
    }

    private void addComponents() {
        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setText(question.getQuestion());
        questionArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(questionArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setBounds(5, 5, getWidth() - 10, getHeight()/3);
        questionPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "Question"));
        questionPanel.add(scrollPane, BorderLayout.CENTER);
        add(questionPanel);

        optionsPanel = new JPanel();
        optionsPanel.setBounds(5, getHeight()/3 + 10, getWidth() - 10, getHeight() - getHeight()/3 - 40);
        optionsPanel.setLayout(null);


        String option = null;
        JRadioButton radioButton = null;
        buttonGroup = new ButtonGroup();
        List options = question.getOptions();
        int top = 10;
        if (options != null && !options.isEmpty()) {
            for (int index=0; index< options.size(); index++) {
                option = (String) options.get(index);
                radioButton = new JRadioButton();
                radioButton.setText(option);
                radioButton.setBounds(10, top, optionsPanel.getWidth() - 30, 26);
                radioButton.setActionCommand(ACTION_COMMANDS[index]);
                buttonGroup.add(radioButton);
                optionsPanel.add(radioButton);
                top = top + 25;
            }
        }

        top = scrollPane.getHeight() + optionsPanel.getHeight() + 140;
        OK = new JButton();
        OK.setText("OK");
        OK.setActionCommand("OK");
        OK.setBounds(5, getHeight() - 26, getWidth() - 10, 24);
        OK.addActionListener(this);

        add(optionsPanel);
        add(OK);

        showComponents(false);
    }

    private void showComponents(boolean show) {
        questionPanel.setVisible(show);
        optionsPanel.setVisible(show);
        OK.setVisible(show);
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        selectedAnswer = null;
        if (actionCommand == null) {
            this.setVisible(false);
        } else {
            if (actionCommand.equalsIgnoreCase("OK")) {
                Enumeration elements = buttonGroup.getElements();
                while (elements.hasMoreElements()) {
                    JRadioButton nextElement = (JRadioButton) elements.nextElement();
                    if (nextElement != null) {
                        if (nextElement.isSelected()) {
                            selectedAnswer = nextElement.getActionCommand();
                            break;
                        }
                    }
                }
            } else if (actionCommand.equalsIgnoreCase("CANCEL")) {
                selectedAnswer = null;
            }
            showComponents(false);
        }
    }

    public void setQuestion(Question question) {
        this.question = question;

        questionArea.setText(question.getQuestion());

        optionsPanel.removeAll();
        String option = null;
        JRadioButton radioButton = null;
        buttonGroup = new ButtonGroup();
        List options = question.getOptions();
        int top = 10;
        if (options != null && !options.isEmpty()) {
            for (int index=0; index< options.size(); index++) {
                option = (String) options.get(index);
                radioButton = new JRadioButton();
                radioButton.setText(option);
                radioButton.setBounds(10, top, optionsPanel.getWidth() - 30, 26);
                radioButton.setActionCommand(ACTION_COMMANDS[index]);
                buttonGroup.add(radioButton);
                optionsPanel.add(radioButton);
                top = top + 25;
            }
        }
        optionsPanel.repaint();
        showComponents(true);
    }

    
}
