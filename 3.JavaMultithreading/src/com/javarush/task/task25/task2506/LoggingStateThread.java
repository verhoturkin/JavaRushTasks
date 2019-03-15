package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;

    public LoggingStateThread(Thread target) {
        super(target);
        this.target = target;
    }

    @Override
    public void run() {
        String oldState;
        System.out.println(oldState = target.getState().name());
        super.run();
        while (target.getState() != State.TERMINATED) {
            String newState = target.getState().name();
            if (!newState.equals(oldState)) {
                oldState = newState;
                System.out.println(newState);
            }
        }
        this.interrupt();
    }
}
