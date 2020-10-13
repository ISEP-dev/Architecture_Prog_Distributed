package client;

import java.io.*;
import java.net.Socket;

public class ClientClass {
    private static final int PORT = 5555;
    private static final String RESOURCE_PATH = "src/main/resources/";

    public static void main(String[] argv) {
        File file = new File(RESOURCE_PATH + "test.txt");
        byte[] bytes = new byte[1024 * 16];

        try {
            Socket socketClient = new Socket("localhost", PORT);
            System.out.println("Client: " + "Connection Established");
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            OutputStream writer = socketClient.getOutputStream();
            writer.flush();

            int count;
            while ((count = reader.read(bytes)) > 0) {
                writer.write(bytes, 0, count);
            }
            System.out.println("** File sent **");
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}