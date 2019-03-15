package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.ArrayList;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> files = new ArrayList<>();

        BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
        String filename = null;
        int maxfilenumber = 0;

        while (!(filename = nameReader.readLine()).equals("end")) {
            files.add(filename);
            int fileNumber = Integer.parseInt(filename.substring(filename.lastIndexOf("part") + 4));
            if (fileNumber > maxfilenumber) {
                maxfilenumber = fileNumber;
            }
        }
        System.out.println(maxfilenumber);
        nameReader.close();

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(files.get(0).substring(0, files.get(0).lastIndexOf(".part"))));
        for (int i = 0; i <= maxfilenumber; i++) {
            for (String file : files) {
                if (Integer.parseInt(file.substring(file.lastIndexOf("part") + 4)) == i) {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    while (bis.available() > 0) {
                        bos.write(bis.read());
                    }
                    bis.close();
                }
            }

        }
        bos.close();
    }
}
