package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Object o) {
        if (o != null) {
            if(o instanceof ApplicationExceptionMessage) {
                String message = ((ApplicationExceptionMessage) o).name();
                return new Exception(message.substring(0,1) + message.substring(1).toLowerCase().replaceAll("_", " "));
            } else if (o instanceof DatabaseExceptionMessage) {
                String message = ((DatabaseExceptionMessage) o).name();
                return new RuntimeException(message.substring(0,1) + message.substring(1).toLowerCase().replaceAll("_", " "));
            } else if (o instanceof UserExceptionMessage) {
                String message = ((UserExceptionMessage) o).name();
                return new Error(message.substring(0,1) + message.substring(1).toLowerCase().replaceAll("_", " "));
            }
        }
        return new IllegalArgumentException();
    }
    }
