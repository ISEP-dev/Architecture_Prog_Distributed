package fr.isep;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class MatrixMultiplication extends UnicastRemoteObject implements MatrixCalculator {

    public MatrixMultiplication() throws RemoteException {
    }

    @Override
    public double[][] multiplication(double[][] firstMatrix, double[][] secondMatrix) throws RemoteException {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                double cell = 0;
                for (int i = 0; i < secondMatrix.length; i++) {
                    cell += firstMatrix[row][i] * secondMatrix[i][col];
                }
                result[row][col] = cell;
            }
        }
        return result;
    }

    @Override
    public String showMatrix(double[][] matrix) throws RemoteException {
        StringBuilder matrixBuilder = new StringBuilder();

        for (double[] row : matrix) {
            matrixBuilder.append(Arrays.toString(row));
        }

        return matrixBuilder.toString();
    }
}
