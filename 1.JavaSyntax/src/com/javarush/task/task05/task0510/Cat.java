package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
*/

public class Cat {
    private String name;
    private int age;
    private int weight;
    private String address;
    private String color;
    private int averageAge = 6;
    private int averageWeight = 3;
    private String averageColor = "gray";

    public static void main(String[] args) {

    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    public void setAverageWeight(int averageWeight) {
        this.averageWeight = averageWeight;
    }

    public void setAverageColor(String averageColor) {
        this.averageColor = averageColor;
    }

    public void initialize(String name) {
        this.name = name;
        this.age = averageAge;
        this.color = averageColor;
        this.weight = averageWeight;
    }

    public void initialize(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.color = averageColor;
        this.weight = weight;
    }

    public void initialize(String name, int age) {
        this.name = name;
        this.age = age;
        this.color = averageColor;
        this.weight = averageWeight;
    }

    public void initialize(int weight, String color) {
        this.age = averageAge;
        this.color = color;
        this.weight = weight;
    }

    public void initialize(int weight, String color, String address) {
        this.age = averageAge;
        this.color = color;
        this.weight = weight;
        this.address = address;
    }
}
