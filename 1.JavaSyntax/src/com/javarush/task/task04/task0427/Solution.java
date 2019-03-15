package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        if ((a % 2) == 0 && a > 0 && a < 10) System.out.println("четное однозначное число");
        if ((a % 2) != 0 && a > 0 && a < 10) System.out.println("нечетное однозначное число");
        if ((a % 2) == 0 && a >= 10 && a < 100) System.out.println("четное двузначное число");
        if ((a % 2) != 0 && a >= 10 && a < 100) System.out.println("нечетное двузначное число");
        if ((a % 2) == 0 && a >= 100 && a < 1000) System.out.println("четное трехзначное число");
        if ((a % 2) != 0 && a >= 100 && a < 1000) System.out.println("нечетное трехзначное число");

    }
}
