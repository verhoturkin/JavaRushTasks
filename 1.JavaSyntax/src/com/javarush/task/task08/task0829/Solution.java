package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        HashMap<String, String> addresses = new HashMap<String, String>();
        while (true) {
            String adress = reader.readLine();
            if (adress.isEmpty()) break;
            String family = reader.readLine();
            if (family.isEmpty()) break;
            addresses.put(adress, family);
        }

        //read home number
        String familyRequest = reader.readLine();

        for (Map.Entry<String, String> pair : addresses.entrySet()) {
            if (pair.getKey().equalsIgnoreCase(familyRequest)) {
                System.out.println(pair.getValue());
            }
        }
    }
}
