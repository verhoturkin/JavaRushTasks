package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        long max = 0;
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
            if (aByte > max) {
                max = aByte;
            }
        }

        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == max) {
                System.out.print(i + " ");
            }
        }
    }
}
