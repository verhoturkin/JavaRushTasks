package com.javarush.task.task05.task0528;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/* 
Вывести на экран сегодняшнюю дату
*/

public class Solution {
    public static void main(String[] args) {
//        Date dateNow = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM YYYY");
//        System.out.println(dateFormat.format(dateNow));
        LocalDate date = LocalDate.now();
        System.out.println(date.format(DateTimeFormatter.ofPattern("dd MM yyyy")));
    }
}
