package com.javarush.task.task05.task0512;

/* 
Создать класс Circle
*/

public class Circle {
    private int centerX;
    private int centerY;
    private int radius;
    private int width;
    private int color;

    public static void main(String[] args) {

    }

    public void initialize(int x, int y, int r) {
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
    }

    public void initialize(int x, int y, int r, int w) {
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
        this.width = w;
    }

    public void initialize(int x, int y, int r, int w, int c) {
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
        this.width = w;
        this.color = c;
    }
}
