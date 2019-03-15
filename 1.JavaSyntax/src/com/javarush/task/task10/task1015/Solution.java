package com.javarush.task.task10.task1015;

import java.util.ArrayList;
import java.util.Random;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() {
        ArrayList<String>[] result = new ArrayList[10];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
            for (int j = 0; j < random.nextInt(10) + 1; j++) {
                result[i].add(Integer.toString(random.nextInt(1000)));
            }
        }

        return result;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}