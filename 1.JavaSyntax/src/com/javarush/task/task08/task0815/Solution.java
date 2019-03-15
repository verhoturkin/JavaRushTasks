package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> names = new HashMap<>();
        names.put("Ivanov", "Ivan");
        names.put("Petrov", "Petr");
        names.put("Sidorov", "Ivan");
        names.put("Popinskiy", "Izya");
        names.put("Mavrov", "Marks");
        names.put("Larin", "Vasya");
        names.put("Putin", "Vladymir");
        names.put("Nikin", "Nikita");
        names.put("Lidskiy", "Kvas");
        names.put("Brestskaya", "Krepost");
        return names;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        int counter = 0;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (pair.getValue().equals(name)) counter++;
        }
        return counter;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {
        int counter = 0;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (pair.getKey().equals(lastName)) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {

    }
}
