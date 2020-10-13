package fr.isep;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server extends UnicastRemoteObject implements MainInterface {
    private static final int PORT = 12345;
    private final List<Student> students = new ArrayList<>();

    protected Server() throws RemoteException {}

    public static void main(String[] args) {
        try {
            Server server = new Server();
            LocateRegistry.createRegistry(PORT);

            Naming.rebind("rmi://localhost:" + PORT + "/student_score", server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student addStudent(int id, String name, int age) {
        Student newStudent = new Student(id, name, age);
        students.add(newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public int promotionScore() {
        int totalScore = 0;
        int numberOfStudents = 0;
        for (Student student : students) {
            if (student.getExams().size() != 0) {
                totalScore += calculateAverage(student);
                ++numberOfStudents;
            }
        }
        return (totalScore / numberOfStudents);
    }

    @Override
    public void addExam(Student student, Exam exam) {
        Student newStudent = getStudent(student.getId());
        if (newStudent == null) {
            System.out.println("Student not found");
        } else {
            newStudent.getExams().add(exam);
        }
    }

    @Override
    public String printExams(Student student) {
        return Arrays.toString(student.getExams().toArray());
    }

    @Override
    public int calculateAverage(Student student) {
        int average = 0;
        double examNumber = 0;

        for (Exam exam : student.getExams()) {
            average += (exam.getScore() * exam.getCoefficient());
            examNumber += exam.getCoefficient();
        }

        return (int) (average / examNumber);
    }
}
