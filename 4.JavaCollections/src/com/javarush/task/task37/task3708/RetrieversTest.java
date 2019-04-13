package com.javarush.task.task37.task3708;

import com.javarush.task.task37.task3708.retrievers.CachingProxyRetriever;
import com.javarush.task.task37.task3708.retrievers.OriginalRetriever;
import com.javarush.task.task37.task3708.retrievers.Retriever;
import com.javarush.task.task37.task3708.storage.RemoteStorage;
import com.javarush.task.task37.task3708.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RetrieversTest {
    private static final int n = 10;

    @Test
    public void testOriginal() {
        Storage storage = new RemoteStorage();
        Retriever retriever = new OriginalRetriever(storage);

        ArrayList<String> original = fill(storage);
        ArrayList<String> expected = retrieve(retriever);

        Assert.assertArrayEquals(expected.toArray(), original.toArray());
    }

    @Test
    public void testCached() {
        Storage storage = new RemoteStorage();
        Retriever retriever = new CachingProxyRetriever(storage);

        ArrayList<String> original = fill(storage);
        ArrayList<String> expected = retrieve(retriever);

        Assert.assertArrayEquals(expected.toArray(), original.toArray());
    }

    public ArrayList<String> fill(Storage storage) {
        ArrayList<String> added = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            storage.add("Resource #" + i);
            added.add("Resource #" + i);

        }
        return added;
    }

    public ArrayList<String> retrieve(Retriever retriever) {
        ArrayList<String> retrieved = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            retrieved.add((String) retriever.retrieve(i));
        }
        return retrieved;
    }

}
