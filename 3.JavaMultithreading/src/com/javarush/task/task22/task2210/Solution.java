package com.javarush.task.task22.task2210;

import java.util.Arrays;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));

    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        int tokens = tokenizer.countTokens();
        String[] result = new String[tokens];
        for (int i = 0; i < tokens; i++) {
            result[i] = tokenizer.nextToken();
        }
        return result;
    }
}
