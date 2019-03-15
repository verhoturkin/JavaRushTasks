package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            strings.add(reader.readLine());
        }
        int max = strings.get(9).length();
        int min = strings.get(9).length();
        int minFirstIndex = 0;
        int maxFirstIndex = 0;
        for (int i = strings.size() - 1; i > -1; i--) {
            if (strings.get(i).length() >= max) {
                max = strings.get(i).length();
                maxFirstIndex = i;
            }
            if (strings.get(i).length() <= min) {
                min = strings.get(i).length();
                minFirstIndex = i;
            }
        }
        if (minFirstIndex < maxFirstIndex) System.out.println(strings.get(minFirstIndex));
        else System.out.println(strings.get(maxFirstIndex));
    }
}

