package fr.isep.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Vector;

public class ServerChat implements Runnable {

    private final Socket socket;
    private static final Vector<BufferedWriter> clients = new Vector<>();

    public ServerChat(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("*** Server is now ON ***");
        ServerSocket mySocket = new ServerSocket(5555);

        while (true) {
            Socket sock = mySocket.accept();
            ServerChat server = new ServerChat(sock);
            Thread serverThread = new Thread(server);
            serverThread.start();
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(writer);

            writer.write("*** Local Chat is ON ***");
            writer.write("\r\n");
            writer.flush();

            while (true) {
                String clientMessage = reader.readLine().trim();
                for (BufferedWriter client : clients) {
                    try {
                        client.write(clientMessage);
                        client.write("\r\n");
                        client.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
