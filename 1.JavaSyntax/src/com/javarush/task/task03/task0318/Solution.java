package com.javarush.task.task03.task0318;

/* 
План по захвату мира
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String sAge = reader.readLine(); //read age
        int nAge = Integer.parseInt(sAge); //convert string age to Int
        String name = reader.readLine(); //read name

        System.out.print(name + " захватит мир через " + nAge + " лет. Му-ха-ха!");


    }
}
