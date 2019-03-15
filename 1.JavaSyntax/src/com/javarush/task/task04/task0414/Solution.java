package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = Integer.parseInt(s);

        if (n % 4 != 0 || (n % 400 != 0 && n % 100 == 0)) System.out.println("количество дней в году: 365");
        else System.out.println("количество дней в году: 366");
    }
}