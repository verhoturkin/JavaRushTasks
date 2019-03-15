package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        String filename = loadFile();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (reader.ready()) {
            String line = reader.readLine();
            ArrayList<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split(" ")));
            int counter = 0;
            for (String s : wordsInLine) {
                if (words.contains(s)) counter++;
            }
            if (counter == 2) System.out.println(line);
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
