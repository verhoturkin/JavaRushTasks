package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.

Пример 1 данных входного файла:
zBk yaz b-kN

Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB

Пример 2 вывода:
abc

Подсказка: использовать TreeSet


Требования:
1. Программа должна использовать класс TreeSet.
2. У объекта типа TreeSet вызови метод add для добавления элементов.
3. Программа должна выводить результат на экран.
4. Вывод программы должен соответствовать условию задачи.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        С этим не проходит валидацию.
//        args = new String[1];
//        args[0] = "C:\\1.txt";

        Solution solution = new Solution();
        solution.sortLettersInFile(args[0]);
    }

    public void sortLettersInFile(String fileName) {
        TreeSet<Character> letters = new TreeSet<>();

        try (FileReader in = new FileReader(fileName)) {
            while (in.ready()) {
                Character c = Character.valueOf((char) in.read());
                c = Character.toLowerCase(c);
                if (Character.isLetter(c)) {
                    letters.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<Character> iterator = letters.iterator();
        for (int i = 0; i < 5; i++) {
            if(iterator.hasNext())
                System.out.print(iterator.next());
        }



    }
}
