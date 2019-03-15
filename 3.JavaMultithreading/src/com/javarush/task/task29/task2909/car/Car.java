package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {

    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;
    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;
    double fuel;
    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers) {
        switch (type) {
            case (TRUCK):
                return new Truck(numberOfPassengers);

            case (SEDAN):
                return new Sedan(numberOfPassengers);

            case (CABRIOLET):
                return new Cabriolet(numberOfPassengers);

            default:
                return null;
        }
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date, SummerStart, SummerEnd)) {
            consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    public double getSummerConsumption(int length) {
        return length * summerFuelConsumption;
    }

    public double getWinterConsumption(int length) {
        return length * winterFuelConsumption + winterWarmingUp;
    }

    public boolean isSummer(Date date, Date SummerStart, Date SummerEnd) {
        return !(date.before(SummerStart) || date.after(SummerEnd));
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred()) {
            return numberOfPassengers;
        } else return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();

    private boolean canPassengersBeTransferred() {
        return (isDriverAvailable() && fuel > 0);
    }
}