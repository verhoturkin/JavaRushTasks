package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int counter = 0, discounter = 0;
        if (a > 0) counter++;
        if (a < 0) discounter++;
        if (b > 0) counter++;
        if (b < 0) discounter++;
        if (c > 0) counter++;
        if (c < 0) discounter++;
        System.out.println("количество отрицательных чисел: " + discounter);
        System.out.println("количество положительных чисел: " + counter);

    }
}
