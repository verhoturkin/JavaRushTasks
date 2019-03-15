package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        HashMap<String, String> result = new HashMap<>();
        result.put("Ivanov", "Ivan");
        result.put("Petrov", "Petr");
        result.put("Sidorov", "Sidr");
        result.put("Pidorov", "Pidr");
        result.put("Salnikov", "Petr");
        result.put("Ivanov", "Maxim");
        result.put("Zarinov", "Jorj");
        result.put("Parinov", "Dima");
        result.put("Vostrikov", "Sergey");
        result.put("Lapulskii", "Pidr");

        return result;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
