package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] twenty = new int[20];
        for (int i = 0; i < twenty.length; i++) {
            twenty[i] = Integer.parseInt(reader.readLine());
        }
        int[] ten1 = new int[10];
        int[] ten2 = new int[10];
        for (int i = 0; i < ten1.length; i++) {
            ten1[i] = twenty[i];
        }
        for (int i = 0; i < ten2.length; i++) {
            ten2[i] = twenty[i + 10];
        }
        for (int i = 0; i < ten2.length; i++) {
            System.out.println(ten2[i]);
        }
    }
}
