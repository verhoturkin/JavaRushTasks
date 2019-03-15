package com.javarush.task.task01.task0133;

/* 
Не думать о секундах…
Напиши код, который считает сколько секунд прошло с 15:00, если на часах 15:30. Выведи результат на экран.


Требования:
1. Программа должна выводить текст.
2. Выведенный текст должен быть целым положительным числом.
3. Выведенное число должно быть кратно 60.
4. Выводимое число должно соответствовать заданию.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getSecondsAfter15(14442));
    }

    public static int getSecondsAfter15(int secondsAfter12) {
        int secondsAfter15;
        secondsAfter15 = secondsAfter12 - (3 * 60 * 60);
        return secondsAfter15;
    }
}