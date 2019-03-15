package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = 0, b = 0, c = 0;
        while (a != -1) {
            a = Integer.parseInt(reader.readLine());
            if (a != -1) {
                b += a;
                c++;
            }

        }
        System.out.println((double) b / c);
    }
}

