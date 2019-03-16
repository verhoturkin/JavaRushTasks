package com.javarush.task.task05.task0505;

/* 
Кошачья бойня
Создать три кота используя класс Cat.
Провести три боя попарно между котами.
Класс Cat создавать не надо. Для боя использовать метод boolean fight(Cat anotherCat).
Результат каждого боя вывести на экран c новой строки.


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. Нужно создать три объекта типа Cat.
3. Нужно провести три боя.
4. Программа должна вывести результат каждого боя с новой строки.
*/

public class Solution {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Choocha", 1, 1, 1);
        Cat cat2 = new Cat("Dupa", 2, 2, 2);
        Cat cat3 = new Cat("Gosha", 3, 3, 3);
        System.out.println(cat1.fight(cat3));
        System.out.println(cat2.fight(cat1));
        System.out.println(cat3.fight(cat1));
    }

    public static class Cat {
        protected String name;
        protected int age;
        protected int weight;
        protected int strength;

        public Cat(String name, int age, int weight, int strength) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.strength = strength;
        }

        public boolean fight(Cat anotherCat) {
            int agePlus = this.age > anotherCat.age ? 1 : 0;
            int weightPlus = this.weight > anotherCat.weight ? 1 : 0;
            int strengthPlus = this.strength > anotherCat.strength ? 1 : 0;

            int score = agePlus + weightPlus + strengthPlus;
            return score > 2; // return score > 2 ? true : false;
        }
    }
}
