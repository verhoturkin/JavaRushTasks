package com.javarush.task.task06.task0610;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Класс ConsoleReader
*/

public class ConsoleReader {
    public static String readString() throws Exception {
        BufferedReader readerString = new BufferedReader(new InputStreamReader(System.in));
        return readerString.readLine();
    }

    public static int readInt() throws Exception {
        BufferedReader readerInt = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(readerInt.readLine());
    }

    public static double readDouble() throws Exception {
        BufferedReader readerDouble = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(readerDouble.readLine());
    }

    public static boolean readBoolean() throws Exception {
        BufferedReader readerBoolean = new BufferedReader(new InputStreamReader(System.in));
        return Boolean.parseBoolean(readerBoolean.readLine());
    }

    public static void main(String[] args) {

    }
}
