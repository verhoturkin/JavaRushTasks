package com.javarush.task.task19.task1909;

/* 
Замена знаков
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

        String words = text.replaceAll("\\W+", "");
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outFile));
        fileWriter.write(words);
        fileWriter.close();

    }
}
