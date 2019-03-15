package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix(byte a, byte b, String c, int d) {
        System.out.println("1");
    }

    public static void printMatrix(double e, double f, String g, int h, int i) {
        System.out.println("2");
    }

    public static void printMatrix(int j, int k, Object l, double m, Object n, Integer o) {
        System.out.println("3");
    }

    public static void printMatrix(int p) {
        System.out.println("4");
    }

    public static void printMatrix(int q, Object r) {
        System.out.println("5");
    }

    public static void printMatrix(Integer s, Integer t, Integer u, long v, double w, int x, long y) {
        System.out.println("6");
    }

    public static void printMatrix(float z, float aa, int bb) {
        System.out.println("7");
    }

    public static void printMatrix(int cc, float dd, String ee, Character ff, char gg, long hh, int ii, Object jj) {
        System.out.println("8");
    }
}
