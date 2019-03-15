package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static void main(String[] args) {
        A a = new A(5, 6);
        B b = new B(9, 8, "ff");
        C c = new C(6, 7, "gh");

        try {
            A cloneA = a.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        try {
            B cloneB = (B) b.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        try {
            C cloneC = c.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (i != a.i) return false;
            return j == a.j;
        }

        @Override
        protected A clone() throws CloneNotSupportedException {
            return (A) super.clone();
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            B b = (B) o;

            return name != null ? name.equals(b.name) : b.name == null;
        }

        @Override
        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone() throws CloneNotSupportedException {
            C clone = new C(getI(), getJ(), getName());
            return clone;
        }
    }
}
