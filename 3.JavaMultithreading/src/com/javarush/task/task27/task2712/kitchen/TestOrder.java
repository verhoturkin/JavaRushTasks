package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        Random random = new Random();
        Dish[] dishesSet = Dish.values();
        for (int i = 1; i < random.nextInt(10); i++) {
            dishes.add(dishesSet[random.nextInt(dishesSet.length)]);
        }
    }
}
