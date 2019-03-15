package com.javarush.task.task31.task3101;

/* 
Проход по дереву файлов
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File newFile = new File(resultFileAbsolutePath.getParent() + "\\allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, newFile);

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            List<File> fileList = new ArrayList<>();
            collectFileList(path, fileList);
            Collections.sort(fileList);

            for (File file : fileList) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    while (fis.available() > 0) {
                        fos.write(fis.read());
                    }
                    fos.write(System.lineSeparator().getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectFileList(File directory, List<File> list) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                collectFileList(file, list);
                continue;
            }
            if (file.length() <= 50) {
                list.add(file);
            }

        }

    }
}
