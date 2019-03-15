package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public static void main(String[] args) {

    }

    public boolean fight(Cat anotherCat) {
        double CatRating = (Cat.this.strength + Cat.this.weight * 0.5 - Cat.this.age * 0.3);
        double anotherCatRating = (anotherCat.strength + anotherCat.weight * 0.5 - anotherCat.age * 0.3);
        if (CatRating >= anotherCatRating) {
            return true;
        } else {
            return false;
        }
    }
}
