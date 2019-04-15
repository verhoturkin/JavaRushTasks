package com.javarush.task.task37.task3711;

public class Computer {
    CPU cpu = new CPU();
    HardDrive hdd = new HardDrive();
    Memory mem = new Memory();

    public void run() {
        cpu.calculate();
        mem.allocate();
        hdd.storeData();
    }
}
