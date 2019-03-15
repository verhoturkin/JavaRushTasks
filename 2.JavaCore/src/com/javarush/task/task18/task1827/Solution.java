package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        String file = loadFile();

        if (args.length > 0) {
            int newId = findMaxId(file) + 1;
            String newLine = String.format("\n%-8s%-30s%-8s%-4s", newId, args[1], args[2], args[3]);
            FileWriter fos = new FileWriter(file, true);
            fos.write(newLine);
            System.out.println(newLine);
            fos.close();
        }
    }

    public static String loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        return filename;
    }

    public static int findMaxId(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        int max = 0;
        while ((line = reader.readLine()) != null) {
            int tmp = Integer.parseInt(line.substring(0, 8).trim());
            if (tmp > max) {
                max = tmp;
            }
        }
        reader.close();
        return max;
    }


}
