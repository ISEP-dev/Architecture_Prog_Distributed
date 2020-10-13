package fr.isep;

import java.rmi.Naming;

public class Client {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            MainInterface obj = (MainInterface) Naming.lookup("rmi://localhost:" + PORT + "/student_score");
            Student studentAdmin = obj.addStudent(0, "Admin", 99);
            Student studentMe = obj.addStudent(1, "Bastien", 22);
            Student studentLucas = obj.addStudent(2, "Lucas", 21);

            System.out.println("Admin : " + studentAdmin);
            System.out.println("Moi : " + studentMe);
            System.out.println("Lucas : " + studentLucas);

            Student getStudent = obj.getStudent(0);
            System.out.println("get Student : " + getStudent.toString());

            Exam englishExam = new Exam("english", 16, 2);
            Exam managementExam = new Exam("management", 10, 1);
            Exam codeExam = new Exam("coding", 19, 5);

            obj.addExam(studentMe, englishExam);
            obj.addExam(studentMe, codeExam);

            obj.addExam(studentLucas, managementExam);
            obj.addExam(studentLucas, englishExam);

            System.out.println(obj.printExams(obj.getStudent(1)));
            System.out.println(obj.printExams(obj.getStudent(2)));

            int myAverage = obj.calculateAverage(obj.getStudent(1));
            System.out.println("My average : " + myAverage);

            System.out.println("Promotion score : " + obj.promotionScore());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
