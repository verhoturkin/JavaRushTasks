package com.javarush.task.task03.task0303;

/* 
Обмен валют
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertEurToUsd(5, 1.5));
        System.out.println(convertEurToUsd(6, 1.8));
    }

    public static double convertEurToUsd(int eur, double course) {
        double usd = (eur * course);
        return usd;
    }
}
