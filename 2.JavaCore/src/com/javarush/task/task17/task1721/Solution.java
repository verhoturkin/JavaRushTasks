package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = nameReader.readLine();
            String fileName2 = nameReader.readLine();
            nameReader.close();
            BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName1));
            String line;
            while ((line = fileReader1.readLine()) != null) {
                allLines.add(line);
            }
            fileReader1.close();
            BufferedReader fileReader2 = new BufferedReader(new FileReader(fileName2));
            String line2;
            while ((line2 = fileReader2.readLine()) != null) {
                forRemoveLines.add(line2);
            }
            fileReader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Solution solution = new Solution();
            solution.joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();

        }
    }
}
