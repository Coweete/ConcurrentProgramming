package Assignment5.Server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A listener that listens for input from a client.
 *
 * @author Jonatan Fridsten
 */
public class ServerListener implements Runnable {

    private BufferedReader bufferedReader;
    private Socket clientSocket;
    private JTextArea textArea;
    private ArrayList<Socket> sockets;
    private volatile int clientNumber;

    /**
     * The constructor for the class.
     *
     * @param clientSocket Socket to client.
     * @param jTextArea    Chat window.
     * @param sockets      Client list.
     * @param clientNumber Client number.
     */
    public ServerListener(Socket clientSocket, JTextArea jTextArea, ArrayList<Socket> sockets, int clientNumber) {
        this.clientSocket = clientSocket;
        this.textArea = jTextArea;
        this.sockets = sockets;
        this.clientNumber = clientNumber;
    }

    /**
     * The run method for the class.
     */
    @Override
    public void run() {
        try {
            //Opens a buffered reader.
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = "";
        try {
            System.out.println("Waiting for input");
            //Will always be here until connection is lost.
            while ((message = bufferedReader.readLine()) != null) {
                System.out.println("Input:" + message);
                textArea.append("Client " + clientNumber + ":" + message + "\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        try {
            //Tries to close the buffer and remove
            //this client from the client list
            bufferedReader.close();
            sockets.remove(clientSocket);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
