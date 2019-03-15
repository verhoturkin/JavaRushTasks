package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");

    }

    public static void main(String[] args) throws IOException {
        String filename = loadFile();

        BufferedReader filereader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = filereader.readLine()) != null) {
            System.out.println(replaceNumbers(line));
        }
        filereader.close();
    }

    public static String loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        return filename;
    }

    public static String replaceNumbers(String string) {
        String[] words = string.split(" ");
        for (int i = 0; i < words.length; i++) {
            try {
                Integer number = Integer.parseInt(words[i]);
                if (map.containsKey(number)) {
                    words[i] = map.get(number);
                }
            } catch (NumberFormatException e) {
                continue;
            }

        }
        String result = "";
        for (String word : words) {
            result = result + word + " ";
        }
        return result;
    }

}

