package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        switch (args[0]) {
            case ("-c"):
                synchronized (allPeople) {
                    createPerson(args);
                }
                break;
            case ("-u"):
                synchronized (allPeople) {
                    updatePerson(args);
                }
                break;
            case ("-d"):
                synchronized (allPeople) {
                    deletePerson(args);
                }
                break;
            case ("-i"):
                synchronized (allPeople) {
                    showPersonInfo(args);
                }
                break;
        }
    }

    private static void createPerson(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDay = null;
        for (int i = 1; i < args.length; i = i + 3) {
            try {
                birthDay = df.parse(args[i + 2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (args[i + 1].equals("м")) {
                allPeople.add(Person.createMale(args[i], birthDay));
            }
            if (args[i + 1].equals("ж")) {
                allPeople.add(Person.createFemale(args[i], birthDay));
            }
            System.out.println(allPeople.size() - 1);
        }
    }

    private static void updatePerson(String[] args) {
        int id;
        Sex sex = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDay = null;

        for (int i = 1; i < args.length; i = i + 4) {
            id = Integer.parseInt(args[i]);
            allPeople.get(id).setName(args[i + 1]);
            if (args[i + 2].equals("м")) sex = Sex.MALE;
            if (args[i + 2].equals("ж")) sex = Sex.FEMALE;
            allPeople.get(id).setSex(sex);
            try {
                birthDay = df.parse(args[i + 3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            allPeople.get(id).setBirthDay(birthDay);
        }
    }

    private static void deletePerson(String[] args) {

        int id;
        for (int i = 1; i < args.length; i++) {
            id = Integer.parseInt(args[i]);
            allPeople.get(id).setName(null);
            allPeople.get(id).setBirthDay(null);
            allPeople.get(id).setSex(null);
        }
    }

    private static void showPersonInfo(String[] args) {

        int id;
        String name;
        String sex;
        String birthDay;
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (int i = 1; i < args.length; i++) {
            id = Integer.parseInt(args[i]);
            name = allPeople.get(id).getName();
            sex = allPeople.get(id).getSex().equals(Sex.MALE) ? "м" : "ж";
            birthDay = df.format(allPeople.get(id).getBirthDay());
            System.out.println(name + " " + sex + " " + birthDay);
        }
    }
}
