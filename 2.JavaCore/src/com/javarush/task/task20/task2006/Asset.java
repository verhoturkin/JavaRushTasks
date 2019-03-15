package com.javarush.task.task20.task2006;

import java.io.Serializable;

public class Asset implements Serializable {
    private String name;
    private double price;

    public Asset(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
