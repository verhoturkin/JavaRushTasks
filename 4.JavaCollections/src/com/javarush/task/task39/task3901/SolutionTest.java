package com.javarush.task.task39.task3901;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void lengthOfLongestUniqueSubstring() {
        int expected1 = 9;
        int expected2 = 3;
        int expected3 = 6;
        int expected4 = 2;
        int expected5 = 1;

        int actual1 = Solution.lengthOfLongestUniqueSubstring("abcdeaouiz");
        int actual2 = Solution.lengthOfLongestUniqueSubstring("123123123");
        int actual3 = Solution.lengthOfLongestUniqueSubstring("a123bcbcqwe");
        int actual4 = Solution.lengthOfLongestUniqueSubstring("ttttwt");
        int actual5 = Solution.lengthOfLongestUniqueSubstring("ggg");

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);
        Assert.assertEquals(expected4, actual4);
        Assert.assertEquals(expected5, actual5);
    }
}