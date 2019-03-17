package com.javarush.task.task36.task3606;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomLoader extends ClassLoader {

    private String path;

    public CustomLoader(String pathToAnimals, ClassLoader parent) {
        super(parent);
        this.path = pathToAnimals;
    }

    public Class<?> load(Path path) throws IOException {
        byte[] b = Files.readAllBytes(path);
        return defineClass(null, b, 0, b.length);
    }
}
