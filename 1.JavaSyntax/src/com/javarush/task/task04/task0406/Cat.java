package com.javarush.task.task04.task0406;

/* 
Программа учета имен
*/

public class Cat {
    private String fullName;

    public static void main(String[] args) {

    }

    public void setName(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;

        this.fullName = fullName;
    }
}
