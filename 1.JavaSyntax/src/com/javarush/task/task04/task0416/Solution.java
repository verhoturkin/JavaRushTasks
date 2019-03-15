package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double a = Double.parseDouble(reader.readLine());
        a = a % 5;

        if (a < 3) System.out.println("зелёный");
        else if (a >= 4) System.out.println("красный");
        else System.out.println("желтый");

    }
}