package com.javarush.task.task20.task2025;
/*
/*
Алгоритмы-числа
*/

public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        for (long i = 1; i < N; i++) {
            int digitsCount = getCountsOfDigits(i);


        }

        return result;
    }

    private static boolean checkArmstrong(long number) {
        int digitsCount = getCountsOfDigits(number);
        long tmp = number;
        long sum = 0;
        while (tmp > 0) {
            sum += Math.pow((double) tmp % 10, (double) digitsCount);
            tmp /= 10;
        }
        if (sum == number) return true;

        return false;
    }

    public static int getCountsOfDigits(long number) {
        return (number == 0) ? 1 : (int) Math.ceil(Math.log10(Math.abs(number) + 0.5));
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        getNumbers(100000000);
        long finish = System.currentTimeMillis();

        System.out.println("Time: " + (start - finish));
    }
}
