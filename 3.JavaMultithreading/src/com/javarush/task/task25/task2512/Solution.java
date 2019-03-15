package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            uncaughtException(t, e.getCause());
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (NullPointerException ex) {
            t.interrupt();
        }
    }
}
