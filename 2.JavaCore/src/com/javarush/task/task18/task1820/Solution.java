package com.javarush.task.task18.task1820;

/* 
Округление чисел
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
        String s = new String(buffer);
        String[] strings = s.split(" ");
        fis.close();

        FileWriter fw = new FileWriter(file2);
        for (String string : strings) {
            fw.write(Math.round(Double.parseDouble(string)) + " ");
        }
        fw.close();

    }
}
