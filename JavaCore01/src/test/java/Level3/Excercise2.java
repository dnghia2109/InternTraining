package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class Excercise2 {
    @Test
    public void case1() {
        int[] nums = {};
        assertThrows(IllegalArgumentException.class, () -> {
            int result = org.example.Level3.Excercise2.findMaximumDifferenceBetweenAny2Elements(nums);
        });
    }

    @Test
    public void case2() {
        int[] nums = {1,5,-3,-4,-5,6,7,-9,-10};
        Assert.assertEquals(17,org.example.Level3.Excercise2.findMaximumDifferenceBetweenAny2Elements(nums));
    }

    @Test
    public void case3() {
        int[] nums = {-1,-9,0,2,-8,-22};
        Assert.assertEquals(24,org.example.Level3.Excercise2.findMaximumDifferenceBetweenAny2Elements(nums));
    }

    @Test
    public void case4() {
        int[] nums = {1,2,3,4,5,6,100};
        Assert.assertEquals(99,org.example.Level3.Excercise2.findMaximumDifferenceBetweenAny2Elements(nums));
    }

}
