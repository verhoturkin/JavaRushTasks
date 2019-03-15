package com.javarush.task.task15.task1522;

public class Sun implements Planet {
    private static volatile Sun instance;

    private Sun() {

    }

    public static Sun getInstance() {
        Sun localInstance = instance;
        if (localInstance == null) {
            synchronized (Sun.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Sun();
                }
            }
        }
        return localInstance;
    }
}
