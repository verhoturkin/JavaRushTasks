package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        String text = "";
        int counter = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inFile = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(inFile);
        while (fileReader.ready()) {
            text = text + (char) fileReader.read();
        }
        fileReader.close();

        String[] words = text.split("\\W");

        for (String word : words) {
            if (word.equals("world")) counter++;
        }

        System.out.println(counter);
    }
}


