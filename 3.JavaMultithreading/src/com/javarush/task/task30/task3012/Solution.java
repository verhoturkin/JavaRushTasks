package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);

    }

    public void createExpression(int number) {
        StringBuilder result = new StringBuilder().append(number).append(" =");
        String[] formats = {"", " + %d", " - %d"};
        for (int i = number, exponent = 1; i > 0; i = ++i / 3, exponent *= 3) {
            result.append(String.format(formats[i % 3], exponent));
        }
        System.out.println(result);


    }
}