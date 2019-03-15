package com.javarush.task.task31.task3109;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();

     /*   try {
            properties.loadFromXML(Files.newInputStream(Paths.get(fileName)));
        } catch (Exception e) {
            try {
                properties.load(Files.newInputStream(Paths.get(fileName)));
            } catch (Exception e1) {

            }
        }*/

        try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
            if (fileName.matches("([^\\s]+(\\.(?i)(xml))$)")) {
                properties.loadFromXML(inputStream);
            } else {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            return properties;
        }
        return properties;
    }
}
