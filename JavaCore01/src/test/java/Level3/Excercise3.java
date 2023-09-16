package Level3;

import org.junit.Assert;
import org.junit.Test;

// Tìm chuỗi con tăng dần dài nhất ( không cần phải là chuỗi các pt liên tiếp, ở vị trí bất kì)
// Input:
public class Excercise3 {
    @Test
    public void case1() {
        int[] numbers = { 3, 10, 2, 1, 20 };
        Assert.assertEquals(3, org.example.Level3.Excercise3.findLISLength(numbers));
    }

    @Test
    public void case2() {
        int[] numbers = {};
        Assert.assertEquals(0, org.example.Level3.Excercise3.findLISLength(numbers));
    }

    @Test
    public void case3() {
        int[] numbers = {0};
        Assert.assertEquals(1, org.example.Level3.Excercise3.findLISLength(numbers));
    }

    @Test
    public void case4() {
        int[] numbers = {2,1};
        Assert.assertEquals(1, org.example.Level3.Excercise3.findLISLength(numbers));
    }

    @Test
    public void case5() {
        int[] numbers = {1,2,3};
        Assert.assertEquals(3, org.example.Level3.Excercise3.findLISLength(numbers));
    }

    @Test
    public void case6() {
        int[] numbers = {-9,8,7,9,2,1,0}; // -9 , 7 , 9
        Assert.assertEquals(3, org.example.Level3.Excercise3.findLISLength(numbers));
    }

}
