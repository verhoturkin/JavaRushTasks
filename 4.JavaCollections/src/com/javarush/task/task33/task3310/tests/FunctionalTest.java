package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        // creating test strings
        String string1 = "kldgjdz ikl kj 89 7 tfkghj";
        String string2 = "вылопарту464%:?6ьж 6жд л657л";
        String string3 = "kldgjdz ikl kj 89 7 tfkghj";

        //get IDs for test strings
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);

        //id2 and id3 shouldn't be equal
        Assert.assertNotEquals(id2, id3);

        //id1 and id3 should be equal
        Assert.assertEquals(id1, id3);

        //get actual strings for test IDs
        String actual1 = shortener.getString(id1);
        String actual2 = shortener.getString(id2);
        String actual3 = shortener.getString(id3);

        //final test
        Assert.assertEquals(string1, actual1);
        Assert.assertEquals(string2, actual2);
        Assert.assertEquals(string3, actual3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

}
