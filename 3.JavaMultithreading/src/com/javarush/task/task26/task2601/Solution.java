package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        Integer[] array = new Integer[]{13, 8, 15, 5, 17, 7};
//        System.out.println(Arrays.toString(sort(array)));

    }

    public static Integer[] sort(Integer[] array) {
        double median = findMedian(array);
//        System.out.println(median);
        Arrays.sort(array, Comparator.comparingDouble(anDouble -> Math.abs(median - anDouble)));
        return array;
    }

    public static double findMedian(Integer[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return ((double) (array[array.length / 2]) + (double) (array[array.length / 2 - 1])) / 2;
        } else return (double) array[array.length / 2];
    }
}
