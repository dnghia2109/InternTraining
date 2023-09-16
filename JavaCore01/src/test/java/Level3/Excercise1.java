package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

// Write a program that takes a list of numbers as input and returns the second smallest number in the list.
// Input: List<Double> => Output: (Double) max 2nd
public class Excercise1 {
    @Test
    public void case1() {
        List<Double> arrNumber = new ArrayList<>(List.of(1.9, 7.9, 1.0, 2.0, 4.0));
        Assert.assertEquals(1.9 , org.example.Level3.Excercise1.findSecondSmallestNum(arrNumber), 0);
    }

    @Test
    public void case2() {
        List<Double> arrNumber = new ArrayList<>(List.of(1.9, 1.9, 1.9));
        Assert.assertEquals(1.9 , org.example.Level3.Excercise1.findSecondSmallestNum(arrNumber), 0);

    }

    @Test
    public void case3() {
        List<Double> arrNumber = new ArrayList<>(List.of());
        //Assert.assertEquals(1.9 , org.example.Level3.Excercise1.findSecondSmallestNum(arrNumber), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            double result = org.example.Level3.Excercise1.findSecondSmallestNum(arrNumber);
        });
    }

    @Test
    public void case4() {
        List<Double> arrNumber = new ArrayList<>(List.of(1.0, 2.0, 1.0, 2.0, 4.0));
        Assert.assertEquals(2 , org.example.Level3.Excercise1.findSecondSmallestNum(arrNumber), 0);
    }
}
