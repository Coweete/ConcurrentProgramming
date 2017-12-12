package Assignment5.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A User interface class for the server.
 *
 * @author Jonatan Fridsten
 */
public class ServerGUI {
    private JFrame frame;
    private JTextArea taChat;
    private JScrollPane scrollPane;
    private JTextField tfMessage;
    private JButton btnSend;
    private Server server;

    /**
     * Default Constructor.
     */
    public ServerGUI() {
    }

    /**
     * Starts the GUI and the server thread.
     */
    public void start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multi chat server");
        initializeGUI();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        server = new Server(9000, taChat);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    /**
     * Initialize the gui components.
     */
    public void initializeGUI() {

        //Adds a text chat
        taChat = new JTextArea();
        taChat.setEditable(false);
        taChat.setBackground(Color.white);
        scrollPane = new JScrollPane(taChat);
        scrollPane.setBounds(10, 15, 475, 275);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        //Adds a message window.
        tfMessage = new JTextField();
        tfMessage.setBounds(10, 310, 375, 30);
        tfMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(tfMessage);

        //Adds a button
        btnSend = new JButton();
        btnSend.setBounds(400, 310, 75, 30);
        btnSend.setText("Send");
        ButtonListener buttonListener = new ButtonListener();
        btnSend.addActionListener(buttonListener);
        frame.add(btnSend);
    }

    /**
     * A private button listener class that listen for button presses.
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object btnPressed = e.getSource();

            if (btnPressed.equals(btnSend)) {
                server.printToClients(tfMessage.getText());
                tfMessage.setText("");
            }
        }
    }
}
