package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        int counter = 0;
        while (fis.available() > 0) {
            int data = fis.read();
            if ((data >= 65 && data <= 90) || (data >= 97 && data <= 122)) {
                counter++;
            }
        }
        fis.close();
        System.out.println(counter);
    }
}
