package Assignment5.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

/**
 * The user interface for the client.
 *
 * @author Jonatan Fridsten
 */
public class ClientGUI {

    private JFrame frame;
    private JTextArea taChat;
    private JScrollPane scrollPane;
    private JTextField tfMessage;
    private JButton btnSend;
    private Socket socket;
    private ClientSender clientSender;
    private ClientListener clientListener;


    /**
     * Default constructor.
     */
    public ClientGUI() {
    }

    /**
     * Starts the GUI and the two listeners.
     */
    public void start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multi chat client");
        initializeGUI();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Shutting down the client");
                clientSender.setRunning(false);
                System.out.println("Done");
            }
        });

        //Tries to start sender and listener
        try {
            System.out.println("Trying to connect to server");
            Socket socket = new Socket("localhost", 9000);
            taChat.append("Connected\n");
            clientSender = new ClientSender(socket, tfMessage, taChat);
            Thread senderThread = new Thread(clientSender);
            clientListener = new ClientListener(socket, taChat);
            Thread listenerThread = new Thread(clientListener);
            senderThread.start();
            listenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Initialize the gui components.
     */
    public void initializeGUI() {

        //The chat window
        taChat = new JTextArea();
        taChat.setEditable(false);
        taChat.setBackground(Color.white);
        scrollPane = new JScrollPane(taChat);
        scrollPane.setBounds(10, 15, 475, 275);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        //The message input.
        tfMessage = new JTextField();
        tfMessage.setBounds(10, 310, 375, 30);
        tfMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(tfMessage);

        //The send button.
        btnSend = new JButton();
        btnSend.setBounds(400, 310, 75, 30);
        btnSend.setText("Send");
        ButtonListener listener = new ButtonListener();
        btnSend.addActionListener(listener);
        frame.add(btnSend);

    }

    /**
     * A private class that listens for button presses.
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object btnPressed = e.getSource();

            if (btnPressed.equals(btnSend)) {
                clientSender.setSending(true);
            }
        }
    }
}
