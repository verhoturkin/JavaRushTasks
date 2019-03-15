package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
  /*      String[] test = new String[1];
        test[0] = "00zz";
        args = test;*/

        try {
            String number = args[0].toUpperCase();

            if (number.length() <= 255) {
                if (number.matches("[0-9A-Z]+")) {
                    char[] chars = number.toCharArray();
                    char max = chars[0];
                    for (char c : chars) {
                        if (c > max)
                            max = c;
                    }
                    if (Character.getNumericValue(max) < 2) System.out.println(2);
                    else System.out.println(Character.getNumericValue(max) + 1);

                } else System.out.println("incorrect");
            }
        } catch (Exception e) {

        }


    }
}