package com.javarush.task.task15.task1522;

public class Moon implements Planet {
    private static volatile Moon instance;

    private Moon() {

    }

    public static Moon getInstance() {
        Moon localInstance = instance;
        if (localInstance == null) {
            synchronized (Moon.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Moon();
                }
            }
        }
        return localInstance;
    }
}

