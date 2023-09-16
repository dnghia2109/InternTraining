package Level2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

// Write a program that takes a list of integers as input
// and returns the maximum sum of any contiguous subarray within the list.
// Input: List<Integer> => Output: int
public class Excercise5 {
    @Test
    public void case1() {
        List<Integer> arrNumber = new ArrayList<>(List.of(1,2,-5,7,6,-4,9));
        assertEquals(18, org.example.Level2.Excercise5.sumContiguousSubarray(arrNumber));
    }

    @Test
    public void case2() {
        List<Integer> arrNumber = new ArrayList<>(List.of(0 , 2, 3, -6));
        assertEquals(5, org.example.Level2.Excercise5.sumContiguousSubarray(arrNumber));
    }

    @Test
    public void case3() {
        List<Integer> arrNumber = new ArrayList<>(List.of());
        assertThrows(IllegalArgumentException.class, () -> {
            int result = org.example.Level2.Excercise5.sumContiguousSubarray(arrNumber);
        });
    }

    @Test
    public void case4() {
        List<Integer> arrNumber = new ArrayList<>(List.of(-1,-2,-3,9,0,5,-6,-7,-8,-9, 11));
        assertEquals(14, org.example.Level2.Excercise5.sumContiguousSubarray(arrNumber));
    }

    @Test
    public void case5() {
        List<Integer> arrNumber = new ArrayList<>(List.of(-2, -3, 4, -1, 2, 1, 5, -3));
        assertEquals(11, org.example.Level2.Excercise5.sumContiguousSubarray(arrNumber));
    }
}
