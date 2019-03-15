package com.javarush.task.task03.task0314;

/* 
Таблица умножения
*/

public class Solution {
    public static void main(String[] args) {
        int a = 1, b = 1;
        while (a < 11) {
            b = 1;
            while (b < 11) {
                System.out.print(a * b + " ");
                b++;
            }
            a++;
            System.out.println("");
        }
    }
}
