package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(0.8, "fgdf");
        labels.put(0.4, "44fff");
        labels.put(3.444, "efdfsdf");
        labels.put(5.445, "gjkfj");
        labels.put(33.44, "gfdjkgj");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
