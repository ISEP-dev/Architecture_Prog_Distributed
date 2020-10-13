package fr.isep;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PromotionInterface extends Remote {
    Student addStudent(int id, String name, int age) throws RemoteException;

    Student getStudent(int id) throws RemoteException;

    int promotionScore() throws RemoteException;
}
