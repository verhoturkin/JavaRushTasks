package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

        while (reader.ready()) {
            String line = reader.readLine();
            ArrayList<String> words = new ArrayList<>(Arrays.asList(line.split(" ")));
            for (String word : words) {
                if (word.matches(".*\\d+.*")) {
                    writer.write(word + " ");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
