package Level2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Write a program that takes a list of numbers as input
// and returns the sum of the numbers that are divisible by both 3 and 5.
// Input: int[] => Output: (int) sum
public class Excercise4 {
    @Test
    public void case1() {
        double[] nums = {1,5,6,7,15,5,3,25,135,60,90};
        assertEquals(300, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

    @Test
    public void case2() {
        double[] nums = {1,5,6,7,15,5,3,25,135,60,90,89.0};
        assertEquals(300, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

    @Test
    public void case3() {
        double[] nums = {0};
        assertEquals(0, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

    @Test
    public void case4() {
        double[] nums = {1,2,3,4,5,-15};
        assertEquals(-15, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

    @Test
    public void case5() {
        double[] nums = {1,2,3,4,5,10};
        assertEquals(0, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

    @Test
    public void case6() {
        double[] nums = {};
        assertEquals(0, org.example.Level2.Excercise4.sumNumDivisibleBoth3And5(nums));
    }

}
