package com.javarush.task.task04.task0415;

/* 
Правило треугольника
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        String s2 = reader.readLine();
        String s3 = reader.readLine();
        int a = Integer.parseInt(s2);
        int b = Integer.parseInt(s1);
        int c = Integer.parseInt(s3);

        if (a >= (b + c) || b >= (a + c) || c >= (a + b)) System.out.println("Треугольник не существует.");
        else System.out.println("Треугольник существует.");

    }
}