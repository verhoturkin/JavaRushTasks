package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании.

Требования:
•	В классе Solution должен существовать метод detectAllWords.
•	В классе Solution должен существовать статический класс Word.
•	Класс Solution не должен содержать статические поля.
•	Метод detectAllWords должен быть статическим.
•	Метод detectAllWords должен быть публичным.
•	Метод detectAllWords должен возвращать список всех слов в кроссворде (согласно условию задачи).
*/

public class Solution {

    public static void main(String[] args) {


        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));

        int[][] crossword2 = new int[][]{
                {'f', 'e', 'e', 'e', 'l', 'e'},
                {'u', 's', 'n', 'n', 'n', 'o'},
                {'l', 'e', 'n', 'o', 'n', 'e'},
                {'m', 'm', 'n', 'n', 'n', 'h'},
                {'p', 'e', 'e', 'e', 'j', 'e'},
        };
        List<Word> words = (detectAllWords(crossword2, "one"));
        for (Word word:words) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();

        for (String word : words) {
            for (int j = 0; j < crossword.length; j++) {
                for (int i = 0; i < crossword[j].length; i++) {
                    if (word.charAt(0) == crossword[j][i]) {
                        list.addAll(findWord(crossword, i,j,word));
                    }
                }
            }
        }

        return list;
    }

    private static List<Word> findWord(int[][] crossword, int i, int j, String word) {
        List<Word> words = new ArrayList<>();

        for (Integer[] direction : Directions.DIRECTIONS) {
            Word result = new Word(word);
            StringBuilder sb = new StringBuilder();
            result.setStartPoint(i, j);
            sb.append(Character.toChars(crossword[j][i]));

            for (int k = 1; k < word.length(); k++) {
                int dx = i + (k * direction[0]);
                int dy = j + (k * direction[1]);
                if (dy >= 0 && dy < crossword.length && dx >= 0 && dx < crossword[j].length && word.charAt(k) == crossword[dy][dx]) {
                    result.setEndPoint(dx, dy);
                    sb.append(Character.toChars(crossword[dy][dx]));
                } else continue;
            }
            if (sb.toString().equals(word))
                words.add(result);
        }
        return words;
    }


    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }

    public static class Directions{
        public static final List<Integer[]> DIRECTIONS = Arrays.asList(new Integer[]{0, -1},
                new Integer[]{1, -1},
                new Integer[]{1, 0},
                new Integer[]{1, 1},
                new Integer[]{0, 1},
                new Integer[]{-1, 1},
                new Integer[]{-1, 0},
                new Integer[]{-1, -1}
                );

    }
}
