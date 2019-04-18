package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.lang.reflect.Method;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        Method method = Object.class.getMethod("jopa");
    }

    public static void main(String[] args) {

    }
}
