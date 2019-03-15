package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static {
        try {
            BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Statics.FILE_NAME)));
            String line;
            while ((line = fileReader.readLine()) != null) {
                lines.add(line);

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
