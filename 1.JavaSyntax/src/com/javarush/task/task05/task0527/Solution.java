package com.javarush.task.task05.task0527;

/* 
Том и Джерри
*/

public class Solution {
    public static void main(String[] args) {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
        Cat tomCat = new Cat("Tom", 6, "male");
        Dog butchDog = new Dog("Butch", 8, "male");
    }

    public static class Mouse {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail) {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    public static class Cat {
        String name;
        int speed;
        String sex;

        public Cat(String name, int speed, String sex) {
            this.name = name;
            this.speed = speed;
            this.sex = sex;
        }
    }

    public static class Dog {
        String name;
        int weight;
        String sex;

        public Dog(String name, int weight, String sex) {
            this.name = name;
            this.weight = weight;
            this.sex = sex;
        }
    }
}
