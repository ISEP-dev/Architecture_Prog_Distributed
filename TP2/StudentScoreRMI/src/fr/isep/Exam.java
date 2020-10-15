package fr.isep;

import java.io.Serializable;

public class Exam implements Serializable {
    private final String name;
    private int score;
    private double coefficient;

    public Exam(String name, int score, double coefficient) {
        this.name = name;
        this.score = score;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", coefficient=" + coefficient +
                '}';
    }
}
