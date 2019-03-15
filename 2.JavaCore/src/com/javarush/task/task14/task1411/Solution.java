package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        HashMap<String, Person> personsMap = new HashMap<>();
        personsMap.put("user", new Person.User());
        personsMap.put("loser", new Person.Loser());
        personsMap.put("coder", new Person.Coder());
        personsMap.put("proger", new Person.Proger());

        while (personsMap.containsKey(key = reader.readLine())) {
            doWork(personsMap.get(key));
        }
    }


    public static void doWork(Person person) {
        if (person instanceof Person.User) {
            ((Person.User) person).live();
        }
        if (person instanceof Person.Loser) {
            ((Person.Loser) person).doNothing();
        }
        if (person instanceof Person.Coder) {
            ((Person.Coder) person).coding();
        }
        if (person instanceof Person.Proger) {
            ((Person.Proger) person).enjoy();
        }
    }
}
