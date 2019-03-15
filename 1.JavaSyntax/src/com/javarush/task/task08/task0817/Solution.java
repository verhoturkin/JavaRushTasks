package com.javarush.task.task08.task0817;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Нам повторы не нужны
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

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        ArrayList<String> tmp = new ArrayList<>(map.values());
        for (String s : tmp) {
            if (Collections.frequency(tmp, s) > 1) removeItemFromMapByValue(map, s);
        }

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

    }
}
