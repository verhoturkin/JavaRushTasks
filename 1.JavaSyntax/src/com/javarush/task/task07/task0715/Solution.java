package com.javarush.task.task07.task0715;

import java.util.ArrayList;
import java.util.Collections;

/* 
Продолжаем мыть раму
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, "мама", "мыла", "раму");
        for (int i = 0; i < strings.size(); i++) {
            strings.add(i + 1, "именно");
            i++;
        }
        strings.forEach(string -> System.out.println(string));
    }
}
