package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> main = new ArrayList<>();
        ArrayList<Integer> three = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();
        ArrayList<Integer> other = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 20; i++) {
            main.add(Integer.parseInt(reader.readLine()));
        }
        for (int i = 0; i < main.size(); i++) {
            if (main.get(i) % 3 == 0) three.add(main.get(i));
            if (main.get(i) % 2 == 0) two.add(main.get(i));
            if (main.get(i) % 2 != 0 && main.get(i) % 3 != 0) other.add(main.get(i));
        }
        printList(three);
        printList(two);
        printList(other);
    }

    public static void printList(List<Integer> list) {
        for (Integer x : list) {
            System.out.println(x);
        }
    }
}
