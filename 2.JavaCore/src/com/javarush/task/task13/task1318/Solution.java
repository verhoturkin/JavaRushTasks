package com.javarush.task.task13.task1318;

import java.io.*;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = reader.readLine();

        InputStream inputStream = new FileInputStream(fileName);

        while (inputStream.available() > 0) {
            char data = (char) inputStream.read();
            System.out.print(data);
        }

        inputStream.close();
        reader.close();

    }
}