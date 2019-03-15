package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File rootDir = new File(root);

        Queue<File> queue = new ArrayDeque<>();
        List<String> filelist = new ArrayList<>();

        Collections.addAll(queue, rootDir.listFiles());

        while (!queue.isEmpty()) {

            File currentFile = queue.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(queue, currentFile.listFiles());
            } else {
                filelist.add(currentFile.getAbsolutePath());
            }
        }
        return filelist;
    }

    public static void main(String[] args) {

    }
}
