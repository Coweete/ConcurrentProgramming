package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class Gui {
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     */
    private JFrame frame;            // The Main window
    private JLabel lblTrans;        // The transmitted text
    private JLabel lblRec;            // The received text
    private JRadioButton bSync;        // The sync radiobutton
    private JRadioButton bAsync;    // The async radiobutton
    private JTextField txtTrans;    // The input field for string to transfer
    private JButton btnRun;         // The run button
    private JButton btnClear;        // The clear button
    private JPanel pnlRes;            // The colored result area
    private JLabel lblStatus;        // The status of the transmission
    private JTextArea listW;        // The write logger pane
    private JScrollPane paneW;      // Makes the output scrollable
    private JTextArea listR;        // The read logger pane
    private JScrollPane paneR;      // Makes the output scrollable
    private Controller controller;  // The controller link

    /**
     * Constructor
     */
    public Gui(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the application
     */
    public void start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 601, 482);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Concurrent Read/Write");
        initializeGUI();                    // Fill in components
        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void initializeGUI() {
        // First, create the static components
        // First the 4 static texts
        ButtonListener buttonListener = new ButtonListener();

        JLabel lab1 = new JLabel("Writer Thread Logger");
        lab1.setBounds(18, 29, 128, 13);
        frame.add(lab1);
        JLabel lab2 = new JLabel("Reader Thread Logger");
        lab2.setBounds(388, 29, 128, 13);
        frame.add(lab2);
        JLabel lab3 = new JLabel("Transmitted:");
        lab3.setBounds(13, 394, 100, 13);
        frame.add(lab3);
        JLabel lab4 = new JLabel("Received:");
        lab4.setBounds(383, 394, 100, 13);
        frame.add(lab4);
        // Then add the two lists (of string) for logging transfer
        listW = new JTextArea();
        listW.setEditable(false);
        paneW = new JScrollPane(listW);
        paneW.setBounds(13, 45, 197, 342);
        paneW.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(paneW);
        listR = new JTextArea();
        listR.setEditable(false);
        paneR = new JScrollPane(listR);
        paneR.setBounds(386, 45, 183, 342);
        paneR.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(paneR);
        // Next the panel that holds the "running" part
        JPanel pnlTest = new JPanel();
        pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
        pnlTest.setBounds(220, 45, 155, 342);
        pnlTest.setLayout(null);
        frame.add(pnlTest);
        lblTrans = new JLabel("Transmitted string goes here");    // Replace with sent string
        lblTrans.setBounds(13, 415, 200, 13);
        frame.add(lblTrans);
        lblRec = new JLabel("Received string goes here");        // Replace with received string
        lblRec.setBounds(383, 415, 200, 13);
        frame.add(lblRec);

        // These are the controls on the user panel, first the radiobuttons
        bSync = new JRadioButton("Syncronous Mode", false);
        bSync.setBounds(8, 37, 131, 17);
        pnlTest.add(bSync);
        bAsync = new JRadioButton("Asyncronous Mode", true);
        bAsync.setBounds(8, 61, 141, 17);
        bAsync.setEnabled(false);
        pnlTest.add(bAsync);
        ButtonGroup grp = new ButtonGroup();
        grp.add(bSync);
        grp.add(bAsync);
        bSync.setSelected(true);
        // then the label and textbox to input string to transfer
        JLabel lab5 = new JLabel("String to Transfer:");
        lab5.setBounds(6, 99, 141, 13);
        pnlTest.add(lab5);
        txtTrans = new JTextField();
        txtTrans.setBounds(6, 124, 123, 20);
        pnlTest.add(txtTrans);
        // The run button
        btnRun = new JButton("Run");
        btnRun.setBounds(26, 150, 75, 23);
        btnRun.addActionListener(buttonListener);
        pnlTest.add(btnRun);
        JLabel lab6 = new JLabel("Running status:");
        lab6.setBounds(23, 199, 110, 13);
        pnlTest.add(lab6);
        // The colored rectangle holding result status
        pnlRes = new JPanel();
        pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlRes.setBounds(26, 225, 75, 47);
        pnlRes.setBackground(Color.WHITE);
        pnlTest.add(pnlRes);
        // also to this text
        lblStatus = new JLabel("Status goes here");
        lblStatus.setBounds(23, 275, 100, 13);
        pnlTest.add(lblStatus);
        // The clear input button, starts disabled
        btnClear = new JButton("Clear");
        btnClear.setBounds(26, 303, 75, 23);
        btnClear.setEnabled(false);
        btnClear.addActionListener(buttonListener);
        pnlTest.add(btnClear);
    }

    /**
     * Triggers the button Run.
     *
     * @param enabled true on,false off
     */
    public void setBtnRunEnabled(boolean enabled) {
        btnRun.setEnabled(enabled);
    }

    /**
     * Triggers the button clear.
     *
     * @param enabled true on,false off
     */
    public void setBtnClearEnabled(boolean enabled) {
        btnClear.setEnabled(enabled);
    }

    /**
     * Returns the input text from the GUI
     *
     * @return Teh input string
     */
    public String getInputText() {
        return txtTrans.getText();
    }

    /**
     * * Adds an text to the text area for the writer
     *
     * @param text Text to be displayed
     */
    public void printWriter(String text) {
        listW.append(text);
    }

    /**
     * Adds an text to the text area for the reader
     *
     * @param text Text to be displayed
     */
    public void printReader(String text) {
        listR.append(text);
    }

    /**
     * Prints the result on the gui
     *
     * @param text result
     */
    public void printReaderString(String text) {
        lblRec.setText(text);
    }

    /**
     * Prints the result on the gui
     *
     * @param text result
     */
    public void printWriterString(String text) {
        lblTrans.setText(text);
    }

    /**
     * Clears the GUI
     */
    public void clearText() {
        listW.setText(null);
        listR.setText(null);
        lblTrans.setText("Transmitted string goes here");
        lblRec.setText("Received string goes here");
    }

    /**
     * Setts the result on the gui
     *
     * @param color   The color of the panel
     * @param message The result message that should be displayed.
     */
    public void setResult(Color color, String message) {
        pnlRes.setBackground(color);
        lblStatus.setText(message);
    }

    /**
     * Class for listening to the buttons
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object btnPressed = e.getSource();

            if (btnPressed.equals(btnRun)) {
                controller.startRun();
            } else if (btnPressed.equals(btnClear)) {
                controller.clear();
            }
        }
    }
}
