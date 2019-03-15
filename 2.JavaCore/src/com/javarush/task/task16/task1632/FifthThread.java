package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FifthThread extends Thread {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        Integer number = 0;
        try {
            while (!(line = reader.readLine()).equals("N")) {
                number += Integer.parseInt(line);
            }
            System.out.println(number);
        } catch (IOException e) {
            System.out.println("Input error");
        }
    }
}

