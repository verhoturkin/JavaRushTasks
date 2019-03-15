package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        if (args[0].equals("-c")) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date birthDay = null;
            try {
                birthDay = df.parse(args[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (args[2].equals("м")) {
                allPeople.add(Person.createMale(args[1], birthDay));
            }
            if (args[2].equals("ж")) {
                allPeople.add(Person.createFemale(args[1], birthDay));
            }
            System.out.println(allPeople.size() - 1);

        } else if (args[0].equals("-u")) {
            int id = Integer.parseInt(args[1]);
            allPeople.get(id).setName(args[2]);
            {
                Sex sex = null;
                if (args[3].equals("м")) sex = Sex.MALE;
                if (args[3].equals("ж")) sex = Sex.FEMALE;
                allPeople.get(id).setSex(sex);
            }
            {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date birthDay = null;
                try {
                    birthDay = df.parse(args[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                allPeople.get(id).setBirthDay(birthDay);
            }

        } else if (args[0].equals("-d")) {
            int id = Integer.parseInt(args[1]);
            allPeople.get(id).setName(null);
            allPeople.get(id).setBirthDay(null);
            allPeople.get(id).setSex(null);

        } else if (args[0].equals("-i")) {
            int id = Integer.parseInt(args[1]);
            String name = allPeople.get(id).getName();
            String sex = allPeople.get(id).getSex().equals(Sex.MALE) ? "м" : "ж";
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String birthDay = df.format(allPeople.get(id).getBirthDay());
            System.out.println(name + " " + sex + " " + birthDay);

        } else System.out.println("Неверный ввод");

    }
}
