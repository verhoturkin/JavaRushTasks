package com.javarush.task.task08.task0827;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        System.out.println(isDateOdd("JANUARY 2 2020"));
    }

    public static boolean isDateOdd(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Date date1 = df.parse(date);
        LocalDate localDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day = localDate.getDayOfYear();
        if (day % 2 != 0) {
            return true;
        } else return false;
    }
}
