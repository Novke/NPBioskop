package rs.np.ac.bg.bioskop_server.server;

import rs.np.ac.bg.bioskop_server.thread.ProcessClientsRequests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    public void startServer() {
        try {
            serverSocket = new ServerSocket(9000);
            while (true) {

                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void stopServer() throws IOException {
        if (serverSocket!=null && !serverSocket.isClosed()) serverSocket.close();
    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        processClientsRequests.start();
    }
}
