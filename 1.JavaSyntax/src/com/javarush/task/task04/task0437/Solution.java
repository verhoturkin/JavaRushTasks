package com.javarush.task.task04.task0437;


/* 
Треугольник из восьмерок
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String s = "8";
        for (int i = 1; i <= 10; i++) {
            System.out.println(s);
            s = s + "8";

        }

    }
}
