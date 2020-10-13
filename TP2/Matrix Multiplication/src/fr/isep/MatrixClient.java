package fr.isep;

import java.rmi.Naming;

public class MatrixClient {
    public static void main(String[] args) {
        try {
            MatrixCalculator calculator = (MatrixCalculator) Naming.lookup("rmi://localhost:1099/MatrixCalculator");


            double[][] firstMatrix = {
                    new double[]{3d, 5d},
                    new double[]{6d, 3d},
                    new double[]{1d, 7d}
            };

            double[][] secondMatrix = {
                    new double[]{1d, 4d, 3d, 7d},
                    new double[]{5d, 2d, 9d, 2d}
            };

            double[][] calculatedMatrix = calculator.multiplication(firstMatrix, secondMatrix);

            System.out.println("Matrix multiplication: " + calculator.printMatrix(calculatedMatrix));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
