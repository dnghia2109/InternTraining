package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Excercise5 {

    @Test
    public void case1() {
        List<Integer> arrNum = new ArrayList<>(List.of());
        Assert.assertEquals(1, org.example.Level3.Excercise5.findPositiveNumNotEqualSumAnyNumInList(arrNum));
    }

    @Test
    public void case2() {
        List<Integer> arrNum = new ArrayList<>(List.of(0, 1, 3, 4, 5));
        Assert.assertEquals(2, org.example.Level3.Excercise5.findPositiveNumNotEqualSumAnyNumInList(arrNum));

        List<Integer> arrNum1 = new ArrayList<>(List.of(1, 4, 5));
        Assert.assertEquals(2, org.example.Level3.Excercise5.findPositiveNumNotEqualSumAnyNumInList(arrNum1));

        List<Integer> arrNum2 = new ArrayList<>(List.of(-9, 3, 4, 5));
        Assert.assertEquals(-8, org.example.Level3.Excercise5.findPositiveNumNotEqualSumAnyNumInList(arrNum2));

    }

    @Test
    public void case3() {
        List<Integer> arrNum = new ArrayList<>(List.of(5));
        Assert.assertEquals(1, org.example.Level3.Excercise5.findPositiveNumNotEqualSumAnyNumInList(arrNum));
    }


}
