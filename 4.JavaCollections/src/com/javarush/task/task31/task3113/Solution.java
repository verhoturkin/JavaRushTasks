package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        final AtomicLong directories = new AtomicLong(0);
        final AtomicLong files = new AtomicLong(0);
        final AtomicLong size = new AtomicLong(0);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());
        reader.close();

        if (Files.isDirectory(path)) {


            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (!dir.equals(path)) {
                        directories.getAndIncrement();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    files.getAndIncrement();
                    size.addAndGet(Files.size(file));
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.printf("Всего папок - %s%n", directories.toString());
            System.out.printf("Всего файлов - %s%n", files.toString());
            System.out.printf("Общий размер - %s%n", size.toString());

        } else System.out.println(path.toAbsolutePath() + " - не папка");

    }
}
