package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {

    private static volatile StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if (instance == null) {
            synchronized (StatisticManager.class) {
                if (instance == null)
                    instance = new StatisticManager();
            }
        }
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private Date dateWithoutTime(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Map<Date, Long> calcDailyAdvIncome() {

        Map<Date, Long> dailyMap = new TreeMap<>(Collections.reverseOrder());

        statisticStorage.getEventList(EventType.SELECTED_VIDEOS).forEach(event -> {
            VideoSelectedEventDataRow castedEvent = (VideoSelectedEventDataRow) event;
            Date date = dateWithoutTime(castedEvent.getDate());

            if (dailyMap.containsKey(date)) {
                dailyMap.put(date, dailyMap.get(date) + castedEvent.getAmount());
            } else dailyMap.put(date, castedEvent.getAmount());
        });

        return dailyMap;
    }

    public Map<Date, Map<String, Integer>> calcCookWorkload() {
        Map<Date, Map<String, Integer>> cookMap = new TreeMap<>(Collections.reverseOrder());

        statisticStorage.getEventList(EventType.COOKED_ORDER).forEach(event -> {
            CookedOrderEventDataRow castedEvent = (CookedOrderEventDataRow) event;
            Date date = dateWithoutTime(castedEvent.getDate());
            String name = castedEvent.getCookName();
            Integer time = castedEvent.getTime();

            if (time > 0) {
                if (!cookMap.containsKey(date)) {
                    Map<String, Integer> inner = new TreeMap<>();
                    inner.put(name, time);
                    cookMap.put(date, inner);
                } else {
                    Map<String, Integer> inner = cookMap.get(date);
                    if (!inner.containsKey(name)) {
                        inner.put(name, time);
                    } else {
                        inner.put(name, inner.get(name) + time);
                    }
                }
            }
        });
        return cookMap;
    }

/*    public void registerMockData() {
        List<Advertisement> mockSet = AdvertisementStorage.getInstance().list();
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 6000, 500, new Date(568984260000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 5300, 500, new Date(569005860000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 500, 500, new Date(569006160000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 6700, 500, new Date(571684560000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 5000, 500, new Date(571684740000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 600, 500, new Date(571771140000L)));
        statisticStorage.put(new VideoSelectedEventDataRow(mockSet, 1400, 500, new Date(571771140000L)));
        List<Dish> mockDishs = new ArrayList<>();
        Collections.addAll(mockDishs, Dish.Soup, Dish.Steak, Dish.Water);
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Vasya", 120, mockDishs, new Date(568984260000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Petya", 125, mockDishs, new Date(569005860000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Kostya", 160, mockDishs, new Date(569006160000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Vasya", 175, mockDishs, new Date(571684560000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Petya", 115, mockDishs,  new Date(571684740000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Kostya", 130, mockDishs, new Date(571771140000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Vasya", 120, mockDishs, new Date(571771140000L)));
        statisticStorage.put(new CookedOrderEventDataRow("Tablet1", "Petya", 155, mockDishs, new Date(571776140000L)));
    }*/

    private class StatisticStorage {

        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> getEventList(EventType type) {
            return storage.get(type);
        }
    }
}
