package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Random rnd = new Random();

        do {
            baos.reset();
            for (int i = 0; i < 8; i++) {
                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                char c = chars.charAt(rnd.nextInt(chars.length()));
                baos.write(c);
            }

        } while (!baos.toString().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"));
        return baos;
    }
}