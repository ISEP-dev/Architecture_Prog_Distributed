package fr.isep;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MatrixServer {
    private static final int PORT = 1099;

    public MatrixServer() {
        try {
            MatrixCalculator calculator = new MatrixMultiplication();
            LocateRegistry.createRegistry(PORT);

            Naming.rebind("rmi://localhost:" + PORT + "/MatrixCalculator", calculator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MatrixServer();
    }
}
