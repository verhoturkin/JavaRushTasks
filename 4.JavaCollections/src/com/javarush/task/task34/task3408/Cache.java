package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key)) {
            Constructor<V> constructor = clazz.getConstructor(key.getClass());
            V obj = constructor.newInstance(key);
            cache.put(key, obj);
        }
        V object = cache.get(key);
        return object;
    }

    public boolean put(V obj) {
        boolean isDone = true;

        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            isDone = false;
        }
        return isDone;
    }

    public int size() {
        return cache.size();
    }
}
