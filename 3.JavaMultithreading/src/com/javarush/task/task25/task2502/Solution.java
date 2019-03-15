package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static void main(String[] args) {
    }

    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws DataFormatException {
            String[] wheels = loadWheelNamesFromDB();
            if (wheels.length != 4) throw new DataFormatException();
            this.wheels = new ArrayList<>();
            for (String wheel : wheels) {
                this.wheels.add(Wheel.valueOf(wheel));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
