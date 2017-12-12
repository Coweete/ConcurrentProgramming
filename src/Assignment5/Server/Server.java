package Assignment5.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The main class for the server that will listen for clients,
 * and if it finds one it will accept it and add a listener to it.
 *
 * @author Jonatan Fridsten
 */
public class Server implements Runnable {

    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private JTextArea jTextArea;
    private ArrayList<Socket> clients;
    private int clientNbr;

    /**
     * The constructor.
     *
     * @param serverPort The server port number.
     * @param jTextArea  Chat window.
     */
    public Server(int serverPort, JTextArea jTextArea) {
        this.jTextArea = jTextArea;
        this.serverPort = serverPort;
        clients = new ArrayList<>();
        clientNbr = 0;
    }

    /**
     * The always running thread.
     */
    @Override
    public void run() {
        openServerSocket();
        jTextArea.append("Waiting for connection...\n");
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                //Tries to accept an client
                clientSocket = this.serverSocket.accept();
                jTextArea.append("New user connected...\n");
                clients.add(clientSocket);
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server stopped");
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            //Adds a new client listener to the thread pool
            threadPool.execute(new ServerListener(clientSocket, jTextArea, clients, clientNbr));
            clientNbr++;
        }
        threadPool.shutdown();
        System.out.println("Server stopped");

    }

    public synchronized void stop() {
        isStopped = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to all the connected clients.
     *
     * @param message The message to be sent.
     */
    public void printToClients(String message) {
        if (clients.size() == 0) {
            jTextArea.append("No clients connected\n");
        } else {
            jTextArea.append("Server:" + message + "\n");
            for (int i = 0; i < clients.size(); i++) {
                try {
                    clients.get(i).getOutputStream().write((message + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Returns if the thread is stopped or not.
     *
     * @return True stopped, False still running
     */
    private synchronized boolean isStopped() {
        return isStopped;
    }

    /**
     * Will try to open a server socket.
     */
    private void openServerSocket() {
        try {
            System.out.println("Trying to open server socket");
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Opened server socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
