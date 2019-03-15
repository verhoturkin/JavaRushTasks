package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        int[] array = new int[256];
        while (fis.available() > 0) {
            array[fis.read()]++;
        }
        fis.close();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                char ch = (char) i;
                System.out.println(ch + " " + array[i]);
            }
        }

    }
}
