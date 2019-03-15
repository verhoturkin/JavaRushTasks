package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof Solution) {
            Solution n = (Solution) o;
            return (first != null ? n.first.equals(first) : n.first == null) && (last != null ? n.last.equals(last) : n.last == null);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 25 * result + (last != null ? last.hashCode() : 0);
        return result;
    }
}
