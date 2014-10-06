package com.quiz.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.quiz.valueobject.Question;

public class QuestionDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private Question question;

    private String selectedAnswer;

    private JTextArea questionArea;

    private JPanel optionsPanel;

    private JButton OK;

    private JButton cancel;

    private ButtonGroup buttonGroup;

    private String[] ACTION_COMMANDS = {"A", "B", "C", "D", "E"};

    public QuestionDialog(JFrame parentFrame, Question question) {
        super(parentFrame, "Question", true);
        this.question = question;
        setLayout(null);
        setSize((int) (ExamLauncher.APP_DIMENSION.getWidth()-100), 340);
        setUndecorated(false);

        setResizable(false);
        setLocationRelativeTo(parentFrame);
        Point p = parentFrame.getLocationOnScreen();
        setLocation(p.x + (parentFrame.getWidth()/2 - getWidth()/2),
                p.y + (parentFrame.getHeight()/2 - getHeight()/2));

        addComponents();
    }

    private void addComponents() {

        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setText(question.getQuestion());
        questionArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(questionArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setBounds(10, 10, getWidth() - 30, 100);
        questionPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "Question"));
        questionPanel.add(scrollPane, BorderLayout.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setBounds(10, 120, getWidth() - 30, 140);
        //optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
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
        OK.setBounds(300, top, 80, 22);
        OK.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        //browse.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        cancel.setActionCommand("CANCEL");
        cancel.setBounds(240 + 80 + 30 , top, 80, 22);
        cancel.addActionListener(this);

        add(questionPanel);
        add(optionsPanel);
        add(OK);
        //add(cancel);

        ImageIcon timerImage = new ImageIcon("D:\\projects\\Quiz\\src\\main\\resources\\timer.gif");
        JLabel timeImageLabel = new JLabel();
        timeImageLabel.setIcon(timerImage);
        timeImageLabel.setBounds(2, getHeight() - 50, 19, 19);
        add(timeImageLabel);

        ImageIcon waitingImage = new ImageIcon("D:\\projects\\Quiz\\src\\main\\resources\\waiting.gif");
        JLabel waitingImageLabel = new JLabel();
        waitingImageLabel.setIcon(waitingImage);
        waitingImageLabel.setBounds(getWidth() - 46, getHeight() - 52, 34, 22);
        add(waitingImageLabel);
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
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
            this.setVisible(false);
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.JDialog#createRootPane()
     */
    protected JRootPane createRootPane() {
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        JRootPane rootPane = new JRootPane();
        rootPane.registerKeyboardAction(this, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(ExamLauncher.APP_DIMENSION);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Question question = new Question();
        question.setQuestion("This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.This is a question.");
        List options = new ArrayList();
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");
        options.add("Option 4");
        options.add("Option 5");
        question.setOptions(options);
        QuestionDialog dialog = new QuestionDialog(frame, question);
        dialog.setVisible(true);
    }
}
