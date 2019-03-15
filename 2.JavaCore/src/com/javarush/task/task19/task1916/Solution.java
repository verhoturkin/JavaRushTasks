package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String file1 = null;
        String file2 = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
             BufferedReader fileReader2 = new BufferedReader(new FileReader(file2))) {

            String line1 = fileReader1.readLine();
            String line2 = fileReader2.readLine();
            String nextLine1 = fileReader1.readLine();
            String nextLine2 = fileReader2.readLine();

            while (line1 != null || line2 != null) {
                if (Objects.equals(line1, line2)) {
                    lines.add(new LineItem(Type.SAME, line1));
                    line1 = nextLine1;
                    nextLine1 = fileReader1.readLine();
                    line2 = nextLine2;
                    nextLine2 = fileReader2.readLine();
                } else if (Objects.equals(line1, nextLine2)) {
                    lines.add(new LineItem(Type.ADDED, line2));
                    line2 = nextLine2;
                    nextLine2 = fileReader2.readLine();
                } else if (Objects.equals(nextLine1, line2)) {
                    lines.add(new LineItem(Type.REMOVED, line1));
                    line1 = nextLine1;
                    nextLine1 = fileReader1.readLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (LineItem line : lines) {
            System.out.println(line.toString());
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return (this.type + " " + this.line);
        }
    }
}
