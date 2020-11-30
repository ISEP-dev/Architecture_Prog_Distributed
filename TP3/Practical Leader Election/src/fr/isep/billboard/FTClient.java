package fr.isep.billboard;

import fr.isep.billboard.interfaces.FTBillboard;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class FTClient {

    private static Registry reg = null;
    private static FTBillboard currentServer = null;
    static List<String> neighbors = new ArrayList<>();
    private static String serverID = null;


    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You need address and port params !");
            System.exit(0);
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);
        serverID = address + ":" + port;

        registerNewServer(address, port);
        connectToServer();

        System.out.println("Starting new client");
        while (true) {
            try {
                neighbors = currentServer.getNeighbors();
                System.out.println("Available neighbors: ");
                neighbors.forEach(System.out::println);

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                System.out.println("Not possible to listen to the server: " + address + ":" + port);
                if (neighbors.size() < 2) {
                    System.out.println("An Error occurs");
                    return;
                }

                String replicaServer = neighbors.get(1);
                String replicaServerAddress = replicaServer.split(":")[0];
                int replicaServerPort = Integer.parseInt(replicaServer.split(":")[1]);
                registerNewServer(replicaServerAddress, replicaServerPort);
                connectToServer();
            }
        }
    }

    private static void registerNewServer(String address, int port) {
        try {
            reg = LocateRegistry.getRegistry(address, port);
        } catch (RemoteException e) {
            System.out.println("Not possible to listen to the server: " + address + ":" + port);
            e.printStackTrace();
        }
    }

    private static void connectToServer() {
        try {
            currentServer = (FTBillboard) reg.lookup(FTBillboard.LOOKUP_NAME);
        } catch (RemoteException e) {
            System.out.println("Impossible to connect to the server");
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
