package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Set<Character> set = new HashSet<>();
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            int tmp = 0;

            for (int j = i; j < s.length(); j++) {
                if(!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                    tmp++;
                } else {
                    set.clear();
                    break;
                }
            }
            max = Integer.max(max, tmp);
        }

        return max;
    }
}
