package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        FileInputStream fis = new FileInputStream(fileName);
        int counter = 0;
        int data;
        while (fis.available() > 0) {
            if ((data = fis.read()) == 44) {
                counter++;
            }
        }

        System.out.println(counter);
        fis.close();
    }
}
