package fr.isep.rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;


public class HelloServeur extends UnicastRemoteObject implements HelloInterface {
    private final String msg;

    public HelloServeur(String msg) throws RemoteException {
        super();
        this.msg = msg;
    }

    public String say() throws RemoteException {
        System.out.println("Hello world: " + msg);
        return "Hello world: " + msg;
    }

    public static void main(String[] args) {
        try {
            // new instance HelloServeur
            HelloServeur obj = new HelloServeur("I'm the HelloServeur");

            // start RMIRegistry: port 12345
            // Alternative: start rmiregistry in terminal !
            LocateRegistry.createRegistry(12345);

            // register the object
            Naming.rebind("rmi://localhost:12345/mon_serveur_hello", obj);

            System.out.println("HelloServer bound in registry");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
