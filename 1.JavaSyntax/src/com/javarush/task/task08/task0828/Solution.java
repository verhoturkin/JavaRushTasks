package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println(s + " is the " + getMonthNumber(s) + " month");
    }

    public static Integer getMonthNumber(String month) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("January", 1);
        map.put("February", 2);
        map.put("March", 3);
        map.put("April", 4);
        map.put("May", 5);
        map.put("June", 6);
        map.put("July", 7);
        map.put("August", 8);
        map.put("September", 9);
        map.put("October", 10);
        map.put("November", 11);
        map.put("December", 12);
        Integer monthNumber = 0;
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (pair.getKey().equalsIgnoreCase(month)) {
                monthNumber = pair.getValue();
            }

        }
        return monthNumber;

    }
}
