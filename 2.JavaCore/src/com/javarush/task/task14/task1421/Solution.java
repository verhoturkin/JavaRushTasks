package com.javarush.task.task14.task1421;

/* 
Singleton
*/
public class Solution {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        Singleton z = Singleton.getInstance();

        System.out.println(s.toString());
        System.out.println(z.toString());
        System.out.println(s.equals(z));
    }
}
