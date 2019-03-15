package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AnimalLoader extends ClassLoader {

    private String pathToAnimals;

    public AnimalLoader(String pathToAnimals, ClassLoader parent) {
        super(parent);
        this.pathToAnimals = pathToAnimals;
    }

    public Class<?> load(Path path) throws IOException {
        byte[] b = Files.readAllBytes(path);
        return defineClass(null, b, 0, b.length);
    }
}

