package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/

public class Solution {
    CountDownLatch latch = new CountDownLatch(1);
    private volatile boolean isWaitingForValue = true;

    public static void main(String[] args) {
        Solution solution = new Solution();
        try {
            solution.someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void someMethod() throws InterruptedException {
        latch.await();
        retrieveValue();

        isWaitingForValue = false;
        latch.countDown();

    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }
}
