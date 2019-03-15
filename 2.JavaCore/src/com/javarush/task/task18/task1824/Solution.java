package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fliename = null;

        while (true) {
            try {
                FileInputStream fis = new FileInputStream(fliename = reader.readLine());
                fis.close();
            } catch (FileNotFoundException e) {
                System.out.println(fliename);
                reader.close();
                break;
            }
        }
    }
}
