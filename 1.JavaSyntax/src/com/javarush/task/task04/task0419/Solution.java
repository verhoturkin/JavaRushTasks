package com.javarush.task.task04.task0419;

/* 
Максимум четырех чисел
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        int a = Input();
        int b = Input();
        int c = Input();
        int d = Input();
        if (a > b) a = a;
        else a = b;
        if (a > c) a = a;
        else a = c;
        if (a > d) a = a;
        else a = d;
        System.out.println(a);
    }

    public static int Input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        int n1 = Integer.parseInt(s1);
        return n1;
    }
}
