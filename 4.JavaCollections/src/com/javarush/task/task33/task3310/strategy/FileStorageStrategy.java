package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    long maxBucketSize;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    static int hash(Long k) {
        int h = k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    final Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new FileBucket();
        }
        transfer(newTable);
        table = newTable;
    }


    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;

        for (int j = 0; j < table.length; j++) {
            if (table[j] != null) continue;

            Entry e = table[j].getEntry();

            while (e != null) {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i].getEntry();
                newTable[i].putEntry(e);
                e = next;
            }
            table[j].remove();
            table[j] = null;
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() >= bucketSizeLimit)
            resize(2 * table.length);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {

        for (int i = 0; i < table.length; i++)
            for (Entry e = table[i].getEntry(); e != null; e = e.next)
                if (Objects.equals(value, e.value))
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int i = indexFor(hash, table.length);

        if (table[i] == null)
            createEntry(hash, key, value, i);
        else {
            for (Entry entry = table[i].getEntry(); entry != null; entry = entry.next) {
                Long k;
                if (entry.hash == hash && ((k = entry.key) == key || key.equals(k)))
                    entry.value = value;
            }
            addEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++)
            for (Entry e = table[i].getEntry(); e != null; e = e.next)
                if (Objects.equals(value, e.value))
                    return e.key;
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
        return (e == null) ? null : e.value;
    }


}
