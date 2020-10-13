package fr.isep.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Random;

public class Mychat implements Runnable {
    public JTextField tx;
    public JTextArea ta;
    public String login;
    BufferedWriter writer;
    BufferedReader reader;

    public static void main(String[] args) {
        try {
            String[] names = {"Astérix", "Obélix", "Numéro bis", "Panoramix", "Idéfix", "Cléopatre", "César"};
            Mychat mychat = new Mychat(names[new Random().nextInt(names.length)]);
            Thread thread = new Thread(mychat);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mychat(String name) {
        login = name;

        JFrame f = new JFrame(name);
        f.setSize(400, 400);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        tx = new JTextField();
        p1.add(tx, BorderLayout.CENTER);

        JButton b1 = new JButton("Send");
        p1.add(b1, BorderLayout.EAST);

        ta = new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);

        f.setContentPane(p2);


        try {
            Socket socketClient = new Socket("localhost", 5555);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.addActionListener(ev -> {
            String string = login + " : " + tx.getText();
            tx.setText("");
            try {
                writer.write(string);
                writer.write("\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        f.setVisible(true);
    }

    @Override
    public void run() {
        try {
            String msgFromServer;
            while ((msgFromServer = reader.readLine()) != null) {
                System.out.println("Message from server: " + msgFromServer);
                ta.append(msgFromServer + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
