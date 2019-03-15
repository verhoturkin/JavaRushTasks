package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = nameReader.readLine();
        String file2 = nameReader.readLine();
        String file3 = nameReader.readLine();
        nameReader.close();

        FileInputStream reader = new FileInputStream(file2);
        FileOutputStream writer = new FileOutputStream(file1);

        byte[] buffer = new byte[reader.available()];
        reader.read(buffer);
        writer.write(buffer);
        reader.close();

        reader = new FileInputStream(file3);
        buffer = new byte[reader.available()];
        reader.read(buffer);
        writer.write(buffer);
        reader.close();
        writer.close();


    }
}
