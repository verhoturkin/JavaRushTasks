package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return READER.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> order = new ArrayList<>();
        String dishName;

        writeMessage("Menu:");
        writeMessage(Dish.allDishesToString());

        do {
            writeMessage("Please enter dish or 'exit':");
            dishName = READER.readLine();
            if (Dish.allDishesToString().contains(dishName)) {
                order.add(Dish.valueOf(dishName));
                writeMessage("Dish added to order.");
            } else if (!dishName.equals("exit"))
                writeMessage("Sorry, we don't have this dish.");

        } while (!dishName.equals("exit"));

        return order;
    }
}
