package com.javarush.task.task23.task2302;

/* 
Запрети переопределение
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class Listener {
        public final void onMouseDown(int x, int y) {
            // Do something when the mouse down event occurs
        }

        public void onMouseUp(int x, int y) {
            // Do something when the mouse up event occurs
        }
    }
}
