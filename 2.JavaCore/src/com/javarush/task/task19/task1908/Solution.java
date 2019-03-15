package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String text = "";


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inFile = reader.readLine();
        String outFile = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(inFile));
        while (fileReader.ready()) {
            text = text + fileReader.readLine();
        }
        fileReader.close();

        String[] words = text.split("\\s");
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outFile));
        for (String word : words) {
            try {
                int tmpInt = Integer.parseInt(word);
                fileWriter.write((tmpInt + " "));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        fileWriter.close();


    }
}


