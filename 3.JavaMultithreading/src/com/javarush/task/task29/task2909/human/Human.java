package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {

    private static int nextId = 0;
    protected int age;
    protected String name;
    protected Size size;
    private int id;
    private BloodGroup bloodGroup;

    private List<Human> children = new ArrayList<>();

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human child) {
        if (child != null) {
            children.add(child);
        }
    }

    public void removeChild(Human child) {
        if (children.contains(child)) {
            children.remove(child);
        }
    }

    @Override
    public void live() {
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return "Человек";
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }

    public class Size {
        public int height;
        public int weight;
    }
}