package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static Flyable result;

    static {
        reset();
    }

    public static void main(String[] args) {

    }

    public static void reset() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int inputIfPlane = 0;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Input error");
        }
        if (input.equalsIgnoreCase("helicopter")) {
            result = new Helicopter();
        }
        if (input.equalsIgnoreCase("plane")) {
            try {
                inputIfPlane = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Input error");
            }
            result = new Plane(inputIfPlane);
        }

    }
}
