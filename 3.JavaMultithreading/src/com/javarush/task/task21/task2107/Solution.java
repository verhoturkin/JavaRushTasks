package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    protected Map<String, User> users = new LinkedHashMap();

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            solution.users.put(entry.getKey(), entry.getValue().clone());
        }
        return solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(users, solution.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            User user = (User) obj;
            if (age != user.age) return false;
            return (name != null ? name.equals(user.name) : user.name == null);

        }

        @Override
        public int hashCode() {
            int result;
            result = age;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            User user = new User(this.age, this.name);
            return user;
        }

        //@Override
        //public String toString() {
        //    return ("Name: " + this.name + " Age: " + this.age);
        //}
    }
}
