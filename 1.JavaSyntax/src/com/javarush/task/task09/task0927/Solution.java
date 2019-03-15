package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> catsMap = new HashMap<>();
        catsMap.put("cat1", new Cat("Cat1"));
        catsMap.put("cat2", new Cat("Cat2"));
        catsMap.put("cat3", new Cat("Cat3"));
        catsMap.put("cat4", new Cat("Cat4"));
        catsMap.put("cat5", new Cat("Cat5"));
        catsMap.put("cat6", new Cat("Cat6"));
        catsMap.put("cat7", new Cat("Cat7"));
        catsMap.put("cat8", new Cat("Cat8"));
        catsMap.put("cat9", new Cat("Cat9"));
        catsMap.put("cat10", new Cat("Cat10"));
        return catsMap;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        HashSet<Cat> catsSet = new HashSet<>();
        for (Map.Entry<String, Cat> pair : map.entrySet()) {
            catsSet.add(pair.getValue());
        }
        return catsSet;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
