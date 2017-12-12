package Assignment5.Client;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * This is a class that will wait for send to be pressed,
 * then a message will be sent to the server.
 *
 * @author Jonatan Fridsten
 */
public class ClientSender implements Runnable {

    private Socket socket;
    private PrintStream writer;
    private volatile boolean isRunning = true;
    private volatile boolean isSending = false;
    private JTextField textField;
    private JTextArea textArea;

    /**
     * The constructor.
     *
     * @param socket     The client socket.
     * @param jTextField The chat display.
     * @param textArea   The message input.
     */
    public ClientSender(Socket socket, JTextField jTextField, JTextArea textArea) {
        this.socket = socket;
        this.textField = jTextField;
        this.textArea = textArea;
    }

    /**
     * The run method.
     */
    @Override
    public void run() {
        try {
            //Tries to create a print stream
            writer = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (isRunning) {
            //Wait for button to be pressed.
            while (!isSending) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Tries to send a message to the server
            System.out.println("Trying to send to tha server");
            String message = textField.getText();
            System.out.println("Message:" + message);
            textArea.append("Me: " + message + "\n");
            writer.println(message);
            textField.setText("");
            isSending = false;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Shutdown
        writer.flush();
        writer.close();
    }

    /**
     * Sets if the thread should be running or not.
     *
     * @param running True = running, False not running.
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * Sets if a button have been pressed or not.
     *
     * @param sending True = send a message, False = wait
     */
    public void setSending(boolean sending) {
        isSending = sending;
    }
}

