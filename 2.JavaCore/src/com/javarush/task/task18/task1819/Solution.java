package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = nameReader.readLine();
        String file2 = nameReader.readLine();
        nameReader.close();

        FileInputStream reader = new FileInputStream(file1);
        FileOutputStream writer = new FileOutputStream(file1);

        byte[] buffer1 = new byte[reader.available()];
        reader.read(buffer1);
        reader.close();

        reader = new FileInputStream(file2);
        byte[] buffer2 = new byte[reader.available()];
        reader.read(buffer2);
        reader.close();

        writer.write(buffer2);
        writer.write(buffer1);
        writer.close();
    }
}
