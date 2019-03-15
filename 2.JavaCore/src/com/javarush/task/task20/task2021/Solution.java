package com.javarush.task.task20.task2021;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* 
Сериализация под запретом
*/
public class Solution implements Serializable {
    public static void main(String[] args) {

    }

    public static class SubSolution extends Solution {


        private void writeObject(ObjectOutputStream objectOutputStream) throws NotSerializableException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream objectInputStream) throws NotSerializableException {
            throw new NotSerializableException();
        }
    }
}
