package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        TreeMap<String, Double> map = new TreeMap<>();
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            String key = line.split(" ")[0];
            Double value = Double.parseDouble(line.split(" ")[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else map.put(key, value);

        }
        fileReader.close();
        Double max = 0.0;
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue() > max) {
                max = pair.getValue();
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue().equals(max)) {
                System.out.println(pair.getKey());
            }
        }
    }
}



