package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
    private static final int PORT = 5555;
    private static final String RESOURCE_PATH = "src/main/resources/";

    public static void main(String[] argv) throws Exception {

        System.out.println(" Server is Running ");
        ServerSocket mysocket = new ServerSocket(PORT);
        while (true) {
            try {
                Socket connectionSocket = mysocket.accept();
                InputStream input = connectionSocket.getInputStream();
                OutputStream output = new FileOutputStream(RESOURCE_PATH + "transfered_test.txt");

                byte[] bytes = new byte[1024 * 16];
                int count;
                while ((count = input.read(bytes)) > 0) {
                    output.write(bytes, 0, count);
                }

                System.out.println("File operation done ");
                connectionSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
    }
}