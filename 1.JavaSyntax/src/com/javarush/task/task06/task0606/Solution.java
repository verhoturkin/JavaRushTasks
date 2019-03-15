package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int a = Integer.parseInt(s);
        int b = 0;
        for (int i = 0; i < s.length(); i++) {
            b = a % 10;
            if ((b % 2) == 0) {
                even++;
            } else odd++;
            a = a / 10;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
