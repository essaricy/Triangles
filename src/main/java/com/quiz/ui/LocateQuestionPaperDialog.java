package com.quiz.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.quiz.util.FileUtil;

public class LocateQuestionPaperDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JLabel locateLable;

    private JTextField locateBox;

    private JButton browse;

    private JButton ok;

    private JButton cancel;

    private ExamLauncher parentFrame;

    public LocateQuestionPaperDialog(ExamLauncher parentFrame) {
        super(parentFrame, true);
        setTitle("Select Questions File");
        this.parentFrame = parentFrame;
        setLayout(null);
        setSize((int) (ExamLauncher.APP_DIMENSION.getWidth()-100), 120);
        setUndecorated(false);

        //setBackground(Color.GRAY);
        setLocationRelativeTo(parentFrame);
        Point p = parentFrame.getLocationOnScreen();
        setLocation(p.x + (parentFrame.getWidth()/2 - getWidth()/2),
                p.y + (parentFrame.getHeight()/2 - getHeight()/2));

        addComponents();
    }

    private void addComponents() {
        int buttonWidth = 90;
        int col1Mark = 60;
        int browseX = getWidth() - 10 - buttonWidth - 10;
        int col2Mark = col1Mark + 20 + 400;

        // Email row
        locateLable = new JLabel();
        locateLable.setText("Locate");
        locateLable.setHorizontalAlignment(JLabel.RIGHT);
        //locateLable.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        locateLable.setBounds(10, 10, col1Mark -10, 22);

        locateBox = new JTextField();
        locateBox.setEditable(false);
        //locateBox.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        locateBox.setBounds(col1Mark + 20, 10, col2Mark, 22);

        browse = new JButton();
        browse.setText("Browse");
        //browse.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        browse.setActionCommand("BROWSE");
        browse.setBounds(browseX, 10, buttonWidth, 22);
        browse.addActionListener(this);

        ok = new JButton();
        ok.setText("OK");
        //browse.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        ok.setActionCommand("OK");
        ok.setBounds(col1Mark + 180, 50, buttonWidth, 22);
        ok.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        //browse.setFont(UiConstants.ARIAL_REGULAR_12_FONT);
        cancel.setActionCommand("CANCEL");
        cancel.setBounds(col1Mark + buttonWidth + 200 , 50, buttonWidth, 22);
        cancel.addActionListener(this);

        add(locateLable);
        add(locateBox);
        add(browse);
        add(ok);
        add(cancel);
    }

    
    /**
     * @return the locateBox
     */
    public String getLocateBoxText() {
        return locateBox.getText();
    }

    /**
     * @param locateBox the locateBox to set
     */
    public void setLocateBoxText(String locateBoxText) {
        if (locateBoxText != null) {
            locateBox.setText(locateBoxText);
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

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == null) {
            this.setVisible(false);
        } else {
            if (actionCommand.equalsIgnoreCase("BROWSE")) {
                setLocateBoxText(FileUtil.browseOpenFile(parentFrame,
                                JFileChooser.FILES_ONLY, getLocateBoxText()));
            } else if (actionCommand.equalsIgnoreCase("OK")) {
                if (parentFrame.loadQuestionPaper(locateBox.getText())) {
                    this.setVisible(false);
                }
            } else if (actionCommand.equalsIgnoreCase("CANCEL")) {
                this.setVisible(false);
            } else {
                this.setVisible(false);
            }
        }
    }
}
