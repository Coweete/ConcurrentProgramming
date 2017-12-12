package Assignment5.Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A class that listens for messages from the server
 *
 * @author Jonatan Fridsten
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private JTextArea jTextArea;
    private BufferedReader reader;

    /**
     * The constructor
     *
     * @param socket    The client socket
     * @param jTextArea Chat window
     */
    public ClientListener(Socket socket, JTextArea jTextArea) {
        this.socket = socket;
        this.jTextArea = jTextArea;
    }

    /**
     * Run method.
     */
    @Override
    public void run() {
        try {
            //Tries to create a buffered reader to listen for input from the server
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = "";
        try {
            //Will always be here until the connection is closed.
            //Listens for input and then prints it out in the chat window.
            while ((message = reader.readLine()) != null) {
                System.out.println("Input:" + message);
                jTextArea.append("Server: " + message + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to close reader");

        //Shutdown
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
