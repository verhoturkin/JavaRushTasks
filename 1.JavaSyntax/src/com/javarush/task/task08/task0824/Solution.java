package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        Human child1 = new Human("Child1", true, 15, new ArrayList<>());
        Human child2 = new Human("Child2", true, 11, new ArrayList<>());
        Human child3 = new Human("Child3", false, 4, new ArrayList<>());
        ArrayList<Human> children = new ArrayList<>();
        Collections.addAll(children, child1, child2, child3);
        Human mother = new Human("mother", false, 35, children);
        Human father = new Human("father", true, 40, children);
        ArrayList<Human> mothers = new ArrayList<>();
        mothers.add(mother);
        ArrayList<Human> fathers = new ArrayList<>();
        fathers.add(father);
        Human grandfather1 = new Human("grandfather1", true, 65, fathers);
        Human grandmother1 = new Human("grandmother1", false, 60, fathers);
        Human grandfather2 = new Human("grandfather2", true, 70, mothers);
        Human grandmother2 = new Human("grandmother2", false, 63, mothers);
        ArrayList<Human> humans = new ArrayList<>();
        Collections.addAll(humans, child1, child2, child3, mother, father, grandfather1, grandfather2, grandmother1, grandmother2);
        for (Human h : humans) {
            System.out.println(h.toString());
        }


    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;


        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
