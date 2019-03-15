package com.javarush.task.task08.task0816;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        map.put("Lenin", df.parse("JULY 2 1934"));
        map.put("Stalin", df.parse("APRIL 10 1890"));
        map.put("Popik", df.parse("JANUARY 9 1966"));
        map.put("Vzizjek", df.parse("NOVEMBER 8 1967"));
        map.put("Pupik", df.parse("SEPTEMBER 7 1989"));
        map.put("Faeton", df.parse("AUGUST 2 1999"));
        map.put("Likantrop", df.parse("MAY 11 1924"));
        map.put("Stepler", df.parse("MARCH 23 1985"));
        map.put("Kirpich", df.parse("DECEMBER 12 1987"));
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        Iterator<Map.Entry<String, Date>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Date> pair = itr.next();
            Date date = pair.getValue();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            if (month >= 6 && month <= 8) itr.remove();
        }

    }

    public static void main(String[] args) {

    }
}
