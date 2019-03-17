package com.javarush.task.task36.task3606;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету.
Имя пакета может содержать File.separator.
В этом пакете кроме скомпилированных классов (.class) могут находиться и другие файлы (например: .java).
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считай все классы с файловой системы, создай фабрику - реализуй метод getHiddenClassObjectByKey.
Примечание: в пакете может быть только один класс, простое имя которого начинается с String key без учета регистра.


Требования:
1. Реализуй метод scanFileSystem, он должен добавлять в поле hiddenClasses найденные классы.
2. Реализуй метод getHiddenClassObjectByKey, он должен создавать объект класса согласно условию задачи.
3. Метод main не изменяй.
4. Метод getHiddenClassObjectByKey не должен кидать исключений.
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        CustomLoader loader = new CustomLoader(packageName, ClassLoader.getSystemClassLoader());

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(URLDecoder.decode(packageName, "UTF-8")))) {
            for (Path path : directoryStream) {
                hiddenClasses.add(loader.load(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass result = null;

            for (Class hiddenClass : hiddenClasses) {
                if(hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                    try {
                        Constructor constructor = hiddenClass.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        result = (HiddenClass) constructor.newInstance();

                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        return result;
    }
}

