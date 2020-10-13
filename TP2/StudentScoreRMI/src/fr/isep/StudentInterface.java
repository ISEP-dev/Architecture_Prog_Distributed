package fr.isep;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote {
    void addExam(Student student, Exam exam) throws RemoteException;

    String printExams(Student student) throws RemoteException;

    int calculateAverage(Student student) throws RemoteException;
}
