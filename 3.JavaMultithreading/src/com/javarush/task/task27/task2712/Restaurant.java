package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Cook cook1 = new Cook("Tolya");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Dasha");
        cook2.setQueue(orderQueue);
        Waiter waiter = new Waiter();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i + 1));
            tablets.get(i).setOrderQueue(orderQueue);

        }
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread orderThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        orderThread.setDaemon(true);
        orderThread.start();

        Thread cook1Thread = new Thread(cook1);
        cook1Thread.setDaemon(true);
        cook1Thread.start();

        Thread cook2Thread = new Thread(cook2);
        cook2Thread.setDaemon(true);
        cook2Thread.start();


        Thread.sleep(2000);

        orderThread.interrupt();

//        StatisticManager.getInstance().registerMockData();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();


    }


}
