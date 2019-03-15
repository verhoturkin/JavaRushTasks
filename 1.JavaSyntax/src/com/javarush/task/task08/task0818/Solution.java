package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Ivanov", 3000);
        map.put("Petrov", 1000);
        map.put("Zinin", 200);
        map.put("Ronin", 540);
        map.put("Monon", 430);
        map.put("Gogol", 6000);
        map.put("Hodor", 213);
        map.put("Loror", 980);
        map.put("Fopop", 234);
        map.put("Polol", 1000);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            if (itr.next().getValue() < 500) {
                itr.remove();
            }
        }
    }

    public static void main(String[] args) {

    }
}