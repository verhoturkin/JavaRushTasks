package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1) + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        AnimalLoader animalLoader = new AnimalLoader(pathToAnimals, ClassLoader.getSystemClassLoader());
        Set<Animal> result = new HashSet<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(URLDecoder.decode(pathToAnimals, "UTF-8")))) {
            for (Path path : directoryStream) {
                Class clazz = animalLoader.load(path);
                if (clazz.getDeclaredConstructor() != null && Animal.class.isAssignableFrom(clazz)) {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    int modifiers = constructor.getModifiers();
                    if (Modifier.isPublic(modifiers))
                        result.add((Animal) constructor.newInstance());
                }
            }

        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }
}
