package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        Map<Date, Long> map = StatisticManager.getInstance().calcDailyAdvIncome();

        map.forEach((date, aLong) -> ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%td-%tb-%tY - %.2f", date, date, date, (double) aLong / 100.00)));
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", map.values().stream().mapToDouble(value -> (double) value / 100.00).sum()));

    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().calcCookWorkload();

        map.forEach(((date, stringIntegerMap) -> {
            ConsoleHelper.writeMessage((String.format(Locale.ENGLISH, "%td-%tb-%tY", date, date, date)));
            stringIntegerMap.forEach((s, integer) -> ConsoleHelper.writeMessage(String.format("%s - %d min", s, integer)));
            ConsoleHelper.writeMessage("");
        }));

    }

    public void printActiveVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getActiveVideoSet();

        list.forEach(adv -> ConsoleHelper.writeMessage(String.format("%s - %d", adv.getName(), adv.getHits())));
    }

    public void printArchivedVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getArchivedVideoSet();

        list.forEach(adv -> ConsoleHelper.writeMessage(adv.getName()));

    }
}
