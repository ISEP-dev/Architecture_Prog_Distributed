package fr.isep;

import java.rmi.Naming;

public class MatrixClient {
    public static void main(String[] args) {
        try {
            MatrixCalculator calculator = (MatrixCalculator) Naming.lookup("rmi://localhost:1099/MatrixCalculator");

            double[][] firstMatrix = {
                    new double[]{5, 1},
                    new double[]{-2, 3},
                    new double[]{3, 7}
            };

            double[][] secondMatrix = {
                    new double[]{1, 6, 0},
                    new double[]{4, 3, -1}
            };

            double[][] calculatedMatrix = calculator.multiplication(firstMatrix, secondMatrix);

            System.out.println("Matrix multiplication: " + calculator.showMatrix(calculatedMatrix));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
