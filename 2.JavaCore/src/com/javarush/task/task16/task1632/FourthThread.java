package com.javarush.task.task16.task1632;

public class FourthThread extends Thread implements Message {
    @Override
    public void showWarning() {
        this.interrupt();
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {

        }
    }
}
