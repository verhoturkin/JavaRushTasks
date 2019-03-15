package com.javarush.task.task22.task2212;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) {
            return false;
        }

        Pattern pt = Pattern.compile("^(\\+\\d{2})?(([(]\\d{3}[)])|\\d{3})\\d{3}[-]?\\d{2}[-]?\\d{2}");
        Matcher mtchr = pt.matcher(telNumber);
        return mtchr.matches();
    }

    public static void main(String[] args) {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("+380501234567");
        numbers.add("+38(050)1234567");
        numbers.add("+38050123-45-67");
        numbers.add("050123-4567");
        numbers.add("+38)050(1234567");
        numbers.add("+38(050)1-23-45-6-7");
        numbers.add("050ххх4567");
        numbers.add("050123456");
        numbers.add("(0)501234567");

        for (String num : numbers) {
            System.out.println(num + " " + checkTelNumber(num));
        }
    }
}

/*
        1) если номер начинается с '+', то он содержит 12 цифр
        2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
        3) может содержать 0-2 знаков '-', которые не могут идти подряд
        4) может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
        5) скобки внутри содержат четко 3 цифры
        6) номер не содержит букв
        7) номер заканчивается на цифру
*/