package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] people = new int[15];
        for (int i = 0; i < people.length; i++) {
            people[i] = Integer.parseInt(reader.readLine());
        }
        int even = 0, odd = 0;
        for (int i = 0; i < people.length; i++) {
            if (i % 2 == 0) {
                even += people[i];
            } else odd += people[i];
        }
        if (even > odd) {
            System.out.println("В домах с четными номерами проживает больше жителей.");
        } else System.out.println("В домах с нечетными номерами проживает больше жителей.");
    }
}
