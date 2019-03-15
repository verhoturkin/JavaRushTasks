package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private int age;
        private boolean sex;
        private String name;
        private double weight;
        private double height;
        private String eyeColor;

        public Human(String name) {
            this.name = name;
        }


        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Human(String name, boolean sex, int age, double weight) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.weight = weight;
        }

        public Human(String name, boolean sex, int age, double weight, double height) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name, boolean sex, int age, double weight, double height, String eyeColor) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.weight = weight;
            this.height = height;
            this.eyeColor = eyeColor;
        }

        public Human(String name, Human anotherHuman) {
            this.name = name;
            this.sex = anotherHuman.sex;
            this.age = anotherHuman.age;
            this.weight = anotherHuman.weight;
            this.height = anotherHuman.height;
            this.eyeColor = anotherHuman.eyeColor;
        }

        public Human(String name, String eyeColor) {
            this.name = name;
            this.eyeColor = eyeColor;
        }

        public Human(String name, boolean sex, int age, Human mother, Human father) {
            this.name = name;
            this.eyeColor = mother.eyeColor;
            this.sex = sex;
            this.height = father.height;
            this.weight = mother.weight;
            this.age = age;
        }

        public Human(String name, double weight, double height) {
            this.name = name;
            this.weight = weight;
            this.height = height;
        }

    }
}
