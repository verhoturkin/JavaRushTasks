package com.javarush.task.task04.task0425;

/* 
Цель установлена!
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(reader.readLine());
        int y = Integer.parseInt(reader.readLine());
        if (x > 0 && y > 0) System.out.println(1);
        if (x < 0 && y > 0) System.out.println(2);
        if (x < 0 && y < 0) System.out.println(3);
        if (x > 0 && y < 0) System.out.println(4);
    }
}
//для первой четверти a>0 и b>0;
//    для второй четверти a<0 и b>0;
//    для третьей четверти a<0 и b<0;
//   для четвертой четверти a>0 и b<0.