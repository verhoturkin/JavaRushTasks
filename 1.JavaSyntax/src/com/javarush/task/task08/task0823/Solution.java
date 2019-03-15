package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] words = s.split(" +");
        String result = "";
        for (String str : words) {
            if (str.length() != 0) {
                result += str.substring(0, 1).toUpperCase() + str.substring(1) + " ";
            }

        }

        System.out.println(result);
    }
}
