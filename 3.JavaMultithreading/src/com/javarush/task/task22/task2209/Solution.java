package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {
        String filename = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            filename = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = getLine(sb.toString().split("\\s+"));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if (words == null || words.length == 0) {
            return sb;
        }
        List<String> temp = new ArrayList<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                String last = temp.get(i).substring(temp.get(i).length() - 1);
                String first = temp.get(j).substring(0, 1);
                if (last.equalsIgnoreCase(first)) {
                    result.add(temp.get(i));
                    result.add(temp.get(j));
                    temp.remove(j);
                    temp.remove(i);
                    i = temp.size();
                    j = temp.size();
                }

            }
        }
        for (int i = 0; i < temp.size(); ) {
            String last = result.get(result.size() - 1).substring(result.get(result.size() - 1).length() - 1);
            String first = temp.get(i).substring(0, 1);
            if (result.contains(temp.get(i))) {
                temp.remove(i);
                i = 0;
            } else if (last.equalsIgnoreCase(first)) {
                result.add(temp.get(i));
                temp.remove(i);
                i = 0;
            } else i++;
        }
        for (String s : result) {
            if (sb.length() == 0) {
                sb.append(s);
            } else sb.append(" " + s);
        }
        for (String s : temp) {
            if (sb.length() == 0) {
                sb.append(s);
            } else sb.append(" " + s);
        }
        return sb;
    }
}
