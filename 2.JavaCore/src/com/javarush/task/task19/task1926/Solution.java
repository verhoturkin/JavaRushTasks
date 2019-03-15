package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = loadFile();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (reader.ready()) {
            StringBuilder sb = new StringBuilder(reader.readLine());
            System.out.println(sb.reverse());
        }
        reader.close();
    }

    public static String loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        return filename;
    }
}
