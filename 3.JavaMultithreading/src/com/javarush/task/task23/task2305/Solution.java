package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        solutions[0] = new Solution();
        solutions[1] = new Solution();
        for (Solution solution : solutions) {
            solution.innerClasses[0] = solution.new InnerClass();
            solution.innerClasses[1] = solution.new InnerClass();
        }
        return solutions;
    }

    public static void main(String[] args) {
        getTwoSolutions();

    }

    public class InnerClass {
    }
}
