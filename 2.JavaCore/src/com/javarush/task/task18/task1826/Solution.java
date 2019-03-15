package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {
        if (args[0].equals("-e")) encrypt(args[1], args[2]);
        if (args[0].equals("-d")) decrypt(args[1], args[2]);
    }

    private static void encrypt(String fileName, String fileOutputName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(fileOutputName);
        while (fis.available() > 0) {
            int tmp = (fis.read() + 1);
            fos.write(tmp);
        }
        fis.close();
        fos.close();
    }

    private static void decrypt(String fileName, String fileOutputName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(fileOutputName);
        while (fis.available() > 0) {
            int tmp = (fis.read() - 1);
            fos.write(tmp);
        }
        fis.close();
        fos.close();
    }
}

/*    private static SecretKey secretKey = null;

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        if (args[0].equals("-e")) encrypt(args[1], args[2]);
        if (args[0].equals("-d")) decrypt(args[1], args[2]);
    }

    private static void encrypt(String fileName, String fileOutputName) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException {
        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(fileOutputName);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] input = new byte[256];
        byte[] output = new byte[256];
        while (fis.available() > 0) {
            int count = fis.read(input);
            output = cipher.doFinal(input);
            fos.write(output, 0, count);
        }
        fos.close();
        fis.close();
    }

    private static void decrypt(String fileName, String fileOutputName) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(fileOutputName);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] input = new byte[256];
        byte[] output = new byte[256];
        while (fis.available() > 0) {
            int count = fis.read(input);
            output = cipher.doFinal(input);
            fos.write(output, 0, count);
        }
        fos.close();
        fis.close();
    }*/


