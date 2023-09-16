package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class Excercise6 {
    @Test
    public void case1() {
        List<Integer> listNums1 = new ArrayList<>();
        List<Integer> listNums2 = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            double result = org.example.Level3.Excercise6.findMedianNumber(listNums2, listNums1);
        });
    }

    @Test
    public void case2() {
        List<Integer> listNums1 = new ArrayList<>(List.of(1, 6,7,98,76,33,23,45,23,24,31));
        List<Integer> listNums2 = new ArrayList<>(List.of(2,4,5,6));

        Assert.assertEquals(23d ,org.example.Level3.Excercise6.findMedianNumber(listNums2, listNums1), 0);
    }

    @Test
    public void case3() {
        List<Integer> listNums1 = new ArrayList<>();
        List<Integer> listNums2 = new ArrayList<>(List.of(2,4,5,6));
        Assert.assertEquals(4.5 ,org.example.Level3.Excercise6.findMedianNumber(listNums2, listNums1), 0);

        List<Integer> listNums3 = new ArrayList<>(List.of(1,7,3,4,8));
        List<Integer> listNums4 = new ArrayList<>(List.of(5,9,2,6,10));
        Assert.assertEquals(5.5 ,org.example.Level3.Excercise6.findMedianNumber(listNums3, listNums4), 0);
    }

    @Test
    public void case4() {
        List<Integer> listNums1 = new ArrayList<>();
        List<Integer> listNums2 = new ArrayList<>(List.of(2));

        Assert.assertEquals(2 ,org.example.Level3.Excercise6.findMedianNumber(listNums2, listNums1), 0);
    }


}
