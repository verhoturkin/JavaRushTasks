package com.javarush.task.task06.task0605;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Контролируем массу тела
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        double weight = Double.parseDouble(bis.readLine());
        double height = Double.parseDouble(bis.readLine());

        Body.massIndex(weight, height);
    }

    public static class Body {
        public static void massIndex(double weight, double height) {
            if (weight / (height * height) < 18.5) {
                System.out.println("Недовес: меньше чем 18.5");
            }
            if (weight / (height * height) >= 18.5 && weight / (height * height) <= 24.9) {
                System.out.println("Нормальный: между 18.5 и 24.9");
            }
            if (weight / (height * height) >= 25 && weight / (height * height) <= 29.9) {
                System.out.println("Избыточный вес: между 25 и 29.9");
            }
            if (weight / (height * height) >= 30) {
                System.out.println("Ожирение: 30 или больше");
            }
        }
    }
}
