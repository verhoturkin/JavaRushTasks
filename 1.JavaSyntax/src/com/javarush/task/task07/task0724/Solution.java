package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        Human male1 = new Human("Вася", true, 34);
        Human male2 = new Human("Игорь", true, 28);
        Human female3 = new Human("Ольга", false, 31);
        Human female4 = new Human("Дарья", false, 33);
        Human male5 = new Human("dskljf", true, 12, male1, female3);
        Human male6 = new Human("fsdfs", true, 11, male1, male2);
        Human female7 = new Human("fdsdf", false, 21, male2, female4);
        Human female8 = new Human("lsdgfj", false, 1, male5, female4);
        Human female9 = new Human("fghfdkj", false, 3, male6, female7);
        System.out.println(male1.toString());
        System.out.println(male2.toString());
        System.out.println(female3.toString());
        System.out.println(female4.toString());
        System.out.println(male5.toString());
        System.out.println(male6.toString());
        System.out.println(female7.toString());
        System.out.println(female8.toString());
        System.out.println(female9.toString());
    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}






















