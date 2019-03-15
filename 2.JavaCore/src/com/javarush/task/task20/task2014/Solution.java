package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    private transient final String pattern = "dd MMMM yyyy, EEEE";
    String string;
    private transient Date currentDate;
    private transient int temperature;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    public static void main(String[] args) {
        System.out.println(new Solution(4));

        try {

            File tmp = File.createTempFile("tmp", null);
            OutputStream out = new FileOutputStream(tmp);
            InputStream in = new FileInputStream(tmp);

            Solution solution = new Solution(4);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(solution);
            out.close();
            oos.close();

            Solution loadedSolution = new Solution(1);
            ObjectInputStream ois = new ObjectInputStream(in);
            loadedSolution = (Solution) ois.readObject();
            in.close();
            ois.close();

            if (loadedSolution.string.equals(solution.string)) System.out.println("YES!!!");
            else System.out.println("NO!!!");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.string;
    }
}
