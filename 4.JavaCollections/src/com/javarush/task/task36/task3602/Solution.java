package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] declaredClasses = Collections.class.getDeclaredClasses();
        for (Class declaredClass : declaredClasses) {
            int modifiers = declaredClass.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPrivate(modifiers) && List.class.isAssignableFrom(declaredClass)) {
                try {
                    Constructor constructor = declaredClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Method method = declaredClass.getDeclaredMethod("get", int.class);
                    method.setAccessible(true);
                    method.invoke(constructor.newInstance(), 0);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    continue;
                } catch (InvocationTargetException e) {
                    if (e.getCause().toString().contains("IndexOutOfBoundsException"))
                        return declaredClass;
                }
            }
        }
        return null;
    }
}
