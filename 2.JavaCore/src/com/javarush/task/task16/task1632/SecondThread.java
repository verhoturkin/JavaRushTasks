package com.javarush.task.task16.task1632;

public class SecondThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(5);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }
}
