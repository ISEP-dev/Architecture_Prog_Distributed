package fr.isep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private final int id;
    private final String name;
    private final List<Exam> exams;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.exams = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Exam> getExams() {
        return exams;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exams=" + exams +
                '}';
    }
}
