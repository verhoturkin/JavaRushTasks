package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    LRUCache cache = new LRUCache(20);
    OriginalRetriever original;

    public CachingProxyRetriever(Storage storage) {
        this.original = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object cached = cache.find(id);
        if (cached != null)
            return cached;

        Object retrieved = original.retrieve(id);
        cache.set(id,retrieved);
        return retrieved;
    }
}
