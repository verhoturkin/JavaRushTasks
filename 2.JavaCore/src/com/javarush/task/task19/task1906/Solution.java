package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inFile = reader.readLine();
        String outFile = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(inFile);
        FileWriter fileWriter = new FileWriter(outFile);

        while (fileReader.ready()) {
            fileReader.read();
            fileWriter.write(fileReader.read());
        }

        fileReader.close();
        fileWriter.close();
    }
}
