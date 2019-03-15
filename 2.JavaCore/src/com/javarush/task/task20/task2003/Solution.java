package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) {
        Solution s = new Solution();
        s.fillInPropertiesMap();
        for (Map.Entry<String, String> stringStringEntry : properties.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " = " + stringStringEntry.getValue());
        }
    }

    public void fillInPropertiesMap() {
        String filename = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            filename = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(filename)) {
            load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        for (Map.Entry<String, String> pair : properties.entrySet()) {
            prop.setProperty(pair.getKey(), pair.getValue());
        }
        prop.store(outputStream, "Properties test");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);
        for (String name : prop.stringPropertyNames()) {
            properties.put(name, prop.getProperty(name));
        }
    }
}
