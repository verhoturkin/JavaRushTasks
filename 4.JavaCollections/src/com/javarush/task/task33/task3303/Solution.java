package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = objectMapper.readValue(new File(fileName), clazz);
        return object;
    }

    public static void main(String[] args) {

    }
}
