package com.javarush.task.task04.task0434;


/* 
Таблица умножения
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int a = 1, b = 1;
        while (a <= 10) {
            while (b <= 10) {
                System.out.print(b * a + " ");
                b++;
            }
            System.out.println("");
            a++;
            b = 1;
        }

    }
}
