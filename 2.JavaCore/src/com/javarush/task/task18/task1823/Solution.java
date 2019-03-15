package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = nameReader.readLine()).equals("exit")) {
            ReadThread readThread = new ReadThread(line);
            readThread.start();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                FileInputStream fileReader = new FileInputStream(fileName);

                long[] bytes = new long[256];
                while (fileReader.available() > 0) bytes[fileReader.read()]++;

                fileReader.close();

                long max = 0;
                Integer result = 0;
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] > max) {
                        max = bytes[i];
                        result = i;
                    }
                }
                synchronized (resultMap) {
                    resultMap.put(fileName, result);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
