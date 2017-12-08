package Assignment5.Server;

public class ServerStart {

    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        serverGUI.start();
        Server server = new Server(9000);
        new Thread(server).start();
    }
}
