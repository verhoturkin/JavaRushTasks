package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("bucket", null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() {
        long size = 0;
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) {
            return null;
        }

        Entry entry = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
