package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        double allSymbols = fis.available();
        double spaceCounter = 0;

        while (fis.available() > 0) {
            if (fis.read() == 32) spaceCounter++;
        }
        fis.close();

        double result = (spaceCounter / allSymbols) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(result));

    }
}
