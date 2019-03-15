package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean containsName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName))
            containsName = false;

        boolean containsContent = true;
        if (partOfContent != null && Files.lines(file).noneMatch(line -> line.contains(partOfContent)))
            containsContent = false;

        boolean biggerThanMinSize = true;
        if (minSize != 0 && content.length < minSize)
            biggerThanMinSize = false;

        boolean smallerThanMaxSize = true;
        if (maxSize != 0 && content.length > maxSize)
            smallerThanMaxSize = false;

        if (containsName && containsContent && biggerThanMinSize && smallerThanMaxSize)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}
