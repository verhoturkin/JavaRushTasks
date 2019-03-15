package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        if (softReference != null) {
            AnyObject anyObject = softReference.get();
            softReference.clear();
            return anyObject;
        } else
            return null;
    }

    public AnyObject put(Long key, AnyObject value) {

        if (cacheMap.containsKey(key)) {
            SoftReference<AnyObject> softReference = cacheMap.get(key);
            AnyObject anyObject = cacheMap.get(key).get();
            softReference.clear();
            return anyObject;
        } else {
            SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
            return null;
        }
    }

    public AnyObject remove(Long key) {

        if (cacheMap.containsKey(key)) {
            SoftReference<AnyObject> softReference = cacheMap.get(key);
            AnyObject anyObject = cacheMap.get(key).get();
            softReference.clear();
            return anyObject;
        } else {
            SoftReference<AnyObject> softReference = cacheMap.remove(key);
            return null;
        }

    }
}