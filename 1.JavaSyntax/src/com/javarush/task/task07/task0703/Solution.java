package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = new String[10];
        int[] numbers = new int[10];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = reader.readLine();
            numbers[i] = strings[i].length();
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
