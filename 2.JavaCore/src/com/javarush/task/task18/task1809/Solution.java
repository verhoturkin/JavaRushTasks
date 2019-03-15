package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = nameReader.readLine();
        String file2 = nameReader.readLine();
        nameReader.close();

        FileInputStream fis = new FileInputStream(file1);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        FileOutputStream fos = new FileOutputStream(file2);
        for (int i = buffer.length - 1; i >= 0; i--) {
            fos.write(buffer, i, 1);
        }
        fos.close();

    }
}
