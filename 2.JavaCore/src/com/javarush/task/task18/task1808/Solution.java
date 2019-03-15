package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = nameReader.readLine();
        String file2 = nameReader.readLine();
        String file3 = nameReader.readLine();
        nameReader.close();

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos1 = new FileOutputStream(file2);
        FileOutputStream fos2 = new FileOutputStream(file3);
        byte[] buffer1;
        if ((fis.available() % 2) != 0) {
            buffer1 = new byte[(fis.available() / 2) + 1];
        } else buffer1 = new byte[fis.available() / 2];
        fis.read(buffer1);
        fos1.write(buffer1);
        fos1.close();

        byte[] buffer2 = new byte[fis.available()];
        fis.read(buffer2);
        fos2.write(buffer2);
        fos2.close();

        fis.close();


    }
}
