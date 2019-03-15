package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        String fileName;
        Integer data;

        {
            BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
            fileName = nameReader.readLine();
            nameReader.close();
        } // читаем с консоли имя файла

        FileInputStream fileReader = new FileInputStream(fileName);

        ArrayList<Integer> bytes = new ArrayList<>();
        while (fileReader.available() > 0) {
            if (!bytes.contains(data = fileReader.read())) {
                bytes.add(data);
            }
        }
        fileReader.close();
        bytes.sort(Comparator.naturalOrder());

        for (Integer aByte : bytes) {
            System.out.print(aByte + " ");
        }
    }

}
