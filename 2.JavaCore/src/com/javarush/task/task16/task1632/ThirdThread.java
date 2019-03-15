package com.javarush.task.task16.task1632;

public class ThirdThread extends Thread {
    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                System.out.println("Ура");
                sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
