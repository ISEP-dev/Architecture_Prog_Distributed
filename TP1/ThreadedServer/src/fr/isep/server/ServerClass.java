package fr.isep.server;

import java.io.*;
import java.net.*;

public class ServerClass implements Runnable {

    private static final int PORT = 5555;
    protected Socket socket;

    public static void main(String[] args) throws IOException {
        System.out.println(" Server is Running ");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true) {
            Socket socket = serverSocket.accept();
            ServerClass server = new ServerClass(socket);
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    public ServerClass(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write( "*** Welcome to the Calculation Server (Addition Only) ***\r\n" );
            writer.write( "*** Please type in the first number and press Enter : \n" );
            writer.flush();
            String data1 = reader.readLine().trim();
            writer.write( "*** Please type in the second number and press Enter :\n" );
            writer.flush();
            String data2 = reader.readLine().trim();
            int num1=Integer.parseInt(data1);
            int num2=Integer.parseInt(data2);
            int result=num1+num2;
            System.out.println( "Addition operation done " );
            writer.write( "\r\n=== Result is : \n" +result+ "\n" );
            writer.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
