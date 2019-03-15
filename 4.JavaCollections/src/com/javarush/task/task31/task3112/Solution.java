package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:\\test"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);

        Path tempfile = Files.createTempFile("download", ".tmp");
        Path target = Files.createFile(downloadDirectory.resolve(urlString.substring(urlString.lastIndexOf("/") + 1)));

        Files.copy(url.openStream(), tempfile, StandardCopyOption.REPLACE_EXISTING);
        Files.move(tempfile, target, StandardCopyOption.REPLACE_EXISTING);

        return target;
    }
}
