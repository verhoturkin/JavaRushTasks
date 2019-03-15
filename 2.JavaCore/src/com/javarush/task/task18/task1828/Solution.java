package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = loadFile();

        if (args[0].equals("-u")) {
            updateEntry(file, args[1], args[2], args[3], args[4]);
        }
        if (args[0].equals("-d")) {
            deleteEntry(file, args[1]);
        }


    }

    public static String loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        return filename;
    }

    public static void deleteEntry(String file, String newId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<String> lines = new ArrayList<>();
        String line = null;
        int id = Integer.parseInt(newId);

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String s : lines) {
            int oldId = Integer.parseInt(s.substring(0, 8).trim());
            if (oldId != id) {
                writer.append(s + "\n");
            }
        }
        reader.close();
        writer.close();

    }

    public static void updateEntry(String file, String newId, String productName, String price, String quantity) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<>();
        String line = null;
        int id = Integer.parseInt(newId);

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String s : lines) {
            int oldId = Integer.parseInt(s.substring(0, 8).trim());
            if (oldId == id) {
                String updatedLine = String.format("%-8s%-30s%-8s%-4s", newId, productName, price, quantity);
                s = updatedLine;
            }
            writer.append(s + "\n");
        }
        reader.close();
        writer.close();

    }

}

