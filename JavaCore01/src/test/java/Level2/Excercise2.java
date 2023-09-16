package Level2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class Excercise2 {

    @Test
    public void case1() {
        String str = "";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = org.example.Level2.Excercise2.findLongestWord(str);
        });
    }

    @Test
    public void case2() {
        String str = " Homm ";
        String result = org.example.Level2.Excercise2.findLongestWord(str);
        Assert.assertEquals("Homm", result);
    }

    @Test
    public void case3() {
        String str = " Homm   Homommm    nay   toi di hoc";
        String result = org.example.Level2.Excercise2.findLongestWord(str);
        Assert.assertEquals("Homommm", result);
    }
}
