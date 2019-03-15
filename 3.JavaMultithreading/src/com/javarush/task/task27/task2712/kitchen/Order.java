package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dishes.isEmpty()) {
            return sb.toString();
        } else {
            sb.append("Your order: ").append(dishes.toString()).append(" of ").append(tablet.toString());
        }
        return sb.toString();
    }

    public int getTotalCookingTime() {
        int duration = 0;

        for (Dish dish : dishes) {
            duration += dish.getDuration();
        }

        return duration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
