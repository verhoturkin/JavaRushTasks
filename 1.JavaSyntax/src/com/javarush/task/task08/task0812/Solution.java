package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.parseInt(reader.readLine()));
        }
        int counter = 1;
        int max = 1;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1).equals(numbers.get(i))) {
                counter++;
                if (counter > max) max = counter;
            } else counter = 1;
        }
        System.out.println(max);
    }
}