package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {

        System.out.println(new PersonScannerAdapter(new Scanner(new File("1.txt"))).read());
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner scanner) {
            this.fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
            String line = null;
            if (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
            }
            String[] parts = line.split("\\s");
            Person person = null;
            try {
                person = new Person(parts[1], parts[2], parts[0], df.parse(parts[3] + parts[4] + parts[5]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
