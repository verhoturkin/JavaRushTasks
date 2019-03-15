package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students;
    private String name;
    private int age;

    public University(String name, int age) {
        students = new ArrayList<>();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student bestStudent = students.get(0);
        for (Student student : students) {
            double a = bestStudent.getAverageGrade();
            double b = student.getAverageGrade();
            if (b > a) {
                bestStudent = student;
            }
        }
        return bestStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        Student worstStudent = students.get(0);
        for (Student student : students) {
            double a = worstStudent.getAverageGrade();
            double b = student.getAverageGrade();
            if (b < a) {
                worstStudent = student;
            }
        }
        return worstStudent;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}