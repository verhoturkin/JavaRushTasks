package com.javarush.task.task15.task1531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/* 
Факториал
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());
        reader.close();

        System.out.println(factorial(input));
    }

    public static String factorial(int n) {
        String result = null;
        if (n < 0) {
            result = "0";
        } else if (n <= 150) {
            BigInteger bigInteger = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
            }
            result = String.valueOf(bigInteger);
        }
        return result;
    }
}
