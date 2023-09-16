package Level2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class Excercise1 {
    // Write a program that takes a list of numbers as input and returns the second largest number in the list.
    //		VD: [70, 11, 20, 4, 100] 👉 70
    @Test
    public void case1() {
        // Mảng truyền vào rỗng
        int[] arr = {};

        assertThrows(IllegalArgumentException.class, () -> {
            int result = org.example.Level2.Excercise1.findSecondLargest(arr);
        });
    }

//    @Test
//    public void case2() {
//        int[] arr = {0,0,0,0};
//        int result = org.example.Level2.Excercise1.findSecondLargest(arr);
//        Assert.assertEquals(0, result);
//    }

    @Test
    public void case3() {
        int[] arr = {-1,0,2,9,10,5,4,-5,-6,-4};
        int result = org.example.Level2.Excercise1.findSecondLargest(arr);
        Assert.assertEquals(9, result);
    }

    @Test
    public void case4() {
        int[] arr = {70, 11, 20, 4, 100};
        int result = org.example.Level2.Excercise1.findSecondLargest(arr);
        Assert.assertEquals(70, result);
    }
}
