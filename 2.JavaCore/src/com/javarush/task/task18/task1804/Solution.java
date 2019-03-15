package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        long min = Long.MAX_VALUE;
        String fileName;

        {
            BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
            fileName = nameReader.readLine();
            nameReader.close();
        } // читаем с консоли имя файла

        FileInputStream fileReader = new FileInputStream(fileName);

        long[] bytes = new long[256];
        while (fileReader.available() > 0) bytes[fileReader.read()]++;
        fileReader.close();

        for (long aByte : bytes) {
            if (aByte > 0 && aByte < min) {
                min = aByte;
            }
        }

        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == min) {
                System.out.print(i + " ");
            }
        }
    }
}