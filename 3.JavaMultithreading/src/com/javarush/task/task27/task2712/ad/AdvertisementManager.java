package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {

  /*  private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> bestSet;
    private long bestAmount;*/

    private static final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> videos = storage.list();
    private int timeSeconds;
    private long maxProfit = 0;
    private int minRemainingTime = timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> bestVariant = new ArrayList<>();
        bestVariant = pickVideosToList(null, null, timeSeconds, 0, bestVariant);
        long totalAmount = 0;
        int totalDuration = 0;
        for (Advertisement ad : bestVariant) {
            ad.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(),
                    ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            totalAmount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestVariant, totalAmount, totalDuration));
    }

    private List<Advertisement> pickVideosToList(List<Advertisement> previousList, Advertisement previousAd, int remainingTime,
                                                 long profit, List<Advertisement> bestResult) throws NoVideoAvailableException {
        List<Advertisement> newList = new ArrayList<>();
        if (previousList != null) {
            newList.addAll(previousList);
            remainingTime -= previousAd.getDuration();
            profit += previousAd.getAmountPerOneDisplaying();
            newList.add(previousAd);
        }
        for (Advertisement ad : videos) {
            if (ad.getHits() < 1) continue;
            if (remainingTime == 0) break;
            if (newList.contains(ad)) continue;
            if (remainingTime >= ad.getDuration())
                bestResult = pickVideosToList(newList, ad, remainingTime, profit, bestResult);
        }
        if (profit > maxProfit) {
            maxProfit = profit;
            minRemainingTime = remainingTime;
            bestResult = newList;
        } else if (profit == maxProfit && remainingTime < minRemainingTime) {
            minRemainingTime = remainingTime;
            bestResult = newList;
        } else if (profit == maxProfit && remainingTime == minRemainingTime && bestResult.size() > newList.size())
            bestResult = newList;
        if (bestResult.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        Collections.sort(bestResult, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long pricePerVideoDiff = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (pricePerVideoDiff != 0)
                    return (int) pricePerVideoDiff;
                else
                    return (int) (o1.getAmountPerOneDisplaying() * 100 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 100 / o2.getDuration());
            }
        });
        return bestResult;

    /*public void processVideos() throws NoVideoAvailableException {

        List<Advertisement> validSet = new ArrayList<>(
                storage.list().stream()
                        .filter(adv -> adv.getAmountPerOneDisplaying() != 0)
                        .collect(Collectors.toList())
        );

        if (validSet.isEmpty()) throw new NoVideoAvailableException();

        findBestSet(validSet);

        Collections.sort(bestSet, Comparator
                .comparingLong(Advertisement::getAmountPerOneDisplaying)
                .thenComparingLong(Advertisement::getDuration)
                .reversed()
        );

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestSet, bestAmount, calcDuration(bestSet)));

        for (Advertisement adv : bestSet) {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    adv.getName(),
                    adv.getAmountPerOneDisplaying(),
                    adv.getAmountPerOneSecond()
            ));
            adv.revalidate();
        }
    }

    private long calcAmount(List<Advertisement> currentSet) {

        return currentSet.stream()
                .mapToLong(Advertisement::getAmountPerOneDisplaying)
                .sum();
    }

    private int calcDuration(List<Advertisement> currentSet) {

        return currentSet.stream()
                .mapToInt(Advertisement::getDuration)
                .sum();
    }

    private void checkSet(List<Advertisement> currentSet) {
        long currentAmount = calcAmount(currentSet);

        if (bestSet == null || currentAmount > bestAmount) {
            bestSet = currentSet;
            bestAmount = currentAmount;

        } else if (currentAmount == bestAmount) {

            if (calcDuration(currentSet) > calcDuration(bestSet)) {

                bestSet = currentSet;

            } else if (calcDuration(currentSet) == calcDuration(bestSet)) {

                if (currentSet.size() < bestSet.size()) {
                    bestSet = currentSet;
                }
            }
        }
    }

    private void findBestSet(List<Advertisement> currentSet) {

        if (currentSet.size() > 0 && calcDuration(currentSet) <= timeSeconds)
            checkSet(currentSet);

        for (int i = 0; i < currentSet.size(); i++) {
            List<Advertisement> newSet = new ArrayList<>(currentSet);
            newSet.remove(i);
            findBestSet(newSet);
        }
    }*/


    }
}
