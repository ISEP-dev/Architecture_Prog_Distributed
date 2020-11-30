package fr.isep.billboard;

import fr.isep.billboard.interfaces.FTBillboard;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    static private Registry reg = null;
    static private FTBillboard currentServer = null;
    static private String serverID = null;


    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("USAGE: Client master port");
            System.exit(0);
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);
        serverID = address + ":" + port;

        try {
            reg = LocateRegistry.getRegistry(address, port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            currentServer = (FTBillboard) reg.lookup(FTBillboard.LOOKUP_NAME);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }


        System.out.println("Starting new client");

        for (int i = 0; i < 1000; i++) {
            String message = "Hello guys " + i, received = "";
            System.out.println("Test with message " + message);

            try {
                currentServer.setMessage(message);
                Thread.sleep(2500);
                received = currentServer.getMessage();
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }

            if (received.equals(message)) {
                System.out.println("no problem");
            } else {
                System.out.println("Problem: " + received + " instead of " + message);
            }
        }
    }
}
