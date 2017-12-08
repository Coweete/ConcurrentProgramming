package Assignment5.Server;

import javax.swing.*;
import java.awt.*;

public class ServerGUI {
    private JFrame frame;
    private JTextArea taChat;
    private JScrollPane scrollPane;
    private JTextField tfMessage;
    private JButton btnSend;

    public ServerGUI(){

    }

    public void start(){
        frame = new JFrame();
        frame.setBounds(0,0,500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multi chat server");
        initializeGUI();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public void initializeGUI(){

        taChat = new JTextArea();
        taChat.setEditable(false);
        taChat.setBackground(Color.white);
        scrollPane = new JScrollPane(taChat);
        scrollPane.setBounds(10,15,475,275);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.add(taChat);
        frame.add(scrollPane);

        tfMessage = new JTextField();
        tfMessage.setBounds(10,310,375,30);
        tfMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(tfMessage);

        btnSend = new JButton();
        btnSend.setBounds(400,310,75,30);
        btnSend.setText("Send");
        frame.add(btnSend);

    }

}
