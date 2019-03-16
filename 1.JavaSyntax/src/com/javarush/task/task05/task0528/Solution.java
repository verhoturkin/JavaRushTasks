package com.javarush.task.task05.task0528;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/* 
Вывести на экран сегодняшнюю дату
Вывести на экран текущую дату в аналогичном виде "21 02 2014".


Требования:
1. Дата должна содержать день, месяц и год, разделенные пробелом.
2. День должен соответствовать текущему.
3. Месяц должен соответствовать текущему.
4. Год должен соответствовать текущему.
5. Вся дата должна быть выведена в одной строке.
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
