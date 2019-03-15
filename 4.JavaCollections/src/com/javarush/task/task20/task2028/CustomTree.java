package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private int size = 0;

    public CustomTree() {
        this.root = new Entry<>("0");
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> queue = new ArrayDeque<>();
        Entry<String> entry = root;
        Entry<String> child = new Entry<>(s);
        queue.add(entry);

        checkTree();

        while (!queue.isEmpty()) {
            if (entry.isAvailableToAddChildren()) {
                child.parent = entry;

                if (entry.availableToAddLeftChildren)
                    entry.leftChild = child;
                else
                    entry.rightChild = child;

                entry.checkChildren();
                size++;
                return true;
            }
            if (entry.leftChild != null)
                queue.add(entry.leftChild);
            if (entry.rightChild != null)
                queue.add(entry.rightChild);
            if (!queue.isEmpty())
                entry = queue.poll();
        }
        return false;
    }


    @Override
    public int size() {
        return this.size;
    }

    public String getParent(String s) {
        try {
            return findEntry(s).parent.elementName;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Entry<String> findEntry(String s) {
        Queue<Entry<String>> queue = new ArrayDeque<>();
        Entry<String> entry = root;
        queue.add(entry);

        while (!queue.isEmpty()) {
            if (entry.elementName.equals(s))
                return entry;
            if (entry.leftChild != null)
                queue.add(entry.leftChild);
            if (entry.rightChild != null)
                queue.add(entry.rightChild);
            if (!queue.isEmpty())
                entry = queue.poll();
        }
        return null;
    }

    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();

        Entry<String> entry;
        Entry<String> parent;

        try {
            entry = findEntry((String) o);
            parent = entry.parent;
            size -= calculateChilds(entry);
            if (parent.leftChild == entry)
                parent.leftChild = null;
            if (parent.rightChild == entry)
                parent.rightChild = null;

            return true;
        } catch (NullPointerException e) {
            return false;

        }

    }

    private int calculateChilds(Entry<String> entry) {

        Queue<Entry<String>> queue = new ArrayDeque<>();
        int counter = 1;

        do {
            if (entry.leftChild != null) {
                queue.add(entry.leftChild);
                counter++;
            }
            if (entry.rightChild != null) {
                queue.add(entry.rightChild);
                counter++;
            }
            if (!queue.isEmpty())
                entry = queue.poll();
        } while (!queue.isEmpty());

        return counter;
    }

    private void checkTree() {
        Queue<Entry<String>> queue = new ArrayDeque<>();
        Entry<String> entry = root;
        queue.add(entry);

        while (!queue.isEmpty()) {
            if (entry.isAvailableToAddChildren())
                return;
            if (entry.leftChild != null)
                queue.add(entry.leftChild);
            if (entry.rightChild != null)
                queue.add(entry.rightChild);
            if (!queue.isEmpty())
                entry = queue.poll();
        }

        entry = root;
        queue.add(entry);

        while (!queue.isEmpty()) {

            entry.availableToAddRightChildren = true;
            entry.availableToAddLeftChildren = true;
            entry.checkChildren();

            if (entry.leftChild != null)
                queue.add(entry.leftChild);
            if (entry.rightChild != null)
                queue.add(entry.rightChild);
            if (!queue.isEmpty())
                entry = queue.poll();
        }


    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;

        }

        public void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;

            if (rightChild != null)
                availableToAddRightChildren = false;

        }

        boolean isAvailableToAddChildren() {
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }
    }

}
