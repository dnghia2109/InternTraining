package Level3;

import org.junit.Assert;
import org.junit.Test;

public class Excercise7 {
    @Test
    public void case1() {
        String string = "";
        Assert.assertEquals(0, org.example.Level3.Excercise7.findLongestPalindromeCount(string));
    }

    @Test
    public void case2() {
        String string = "1";
        Assert.assertEquals(1, org.example.Level3.Excercise7.findLongestPalindromeCount(string));

        String string1 = "cd";
        Assert.assertEquals(1, org.example.Level3.Excercise7.findLongestPalindromeCount(string1));

        String string2 = "121";
        Assert.assertEquals(3, org.example.Level3.Excercise7.findLongestPalindromeCount(string2));
    }

    @Test
    public void case3() {
        String string = "121 abcbcd";
        Assert.assertEquals(7, org.example.Level3.Excercise7.findLongestPalindromeCount(string));
    }

    @Test
    public void case4() {
        String string = "A man a plan a canal Panama 3456654";
        Assert.assertEquals(27, org.example.Level3.Excercise7.findLongestPalindromeCount(string));
    }
}
