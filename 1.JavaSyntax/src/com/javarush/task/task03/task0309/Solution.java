package com.javarush.task.task03.task0309;

/* 
Сумма 5 чисел
Вывести на экран сумму чисел от 1 до 5 построчно (должно быть 5 строк):
1
1+2=3
1+2+3=6
...

Пример вывода:
1
3
6
...


Требования:
1. Программа должна выводить текст.
2. Выведенный текст должен содержать 5 строк.
3. Число в каждой новой строке должно быть больше предыдущего.
4. Выводимый текст должен соответствовать заданию.
*/

public class Solution {
    public static void main(String[] args) {
        int x = 0;
        for (int b = 1; b < 11; b++) {
            System.out.println(x = x + b);
        }
    }
}
