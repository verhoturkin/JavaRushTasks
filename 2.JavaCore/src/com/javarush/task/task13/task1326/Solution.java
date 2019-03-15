package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = reader.readLine();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        ArrayList<Integer> numbers = new ArrayList<>();

        String line;

        while ((line = fileReader.readLine()) != null) {
            Integer readInt = Integer.parseInt(line);
            numbers.add(readInt);
        }

        numbers.sort(Comparator.naturalOrder());
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }

        fileReader.close();
        reader.close();
    }
}
