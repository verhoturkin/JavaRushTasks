package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        HashMap<Character, Integer> alphabet = new HashMap<>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.put(abcArray[i], 0);
        }

        // ввод строк
        StringBuilder longWord = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            longWord.append(s);
        }

        for (int i = 0; i < longWord.length(); i++) {
            Character c = longWord.charAt(i);
            if (!Character.isSpaceChar(c) && alphabet.containsKey(c)) {
                alphabet.put(c, alphabet.get(c) + 1);
            }
        }

        for (Character letter : abcArray) {
            System.out.println(letter + " " + alphabet.get(letter));
        }
    }
}
