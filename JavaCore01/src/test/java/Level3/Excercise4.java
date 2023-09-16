package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class Excercise4 {
    @Test
    public void case1() {
        String[] strings = { "hello", "world", "rfooba", "rbafoo", "he", "llo" };
        Assert.assertEquals(new ArrayList<>(List.of("rfooba", "rbafoo")), org.example.Level3.Excercise4.findMostCommonStrings(strings));
    }

    @Test
    public void case2() {
        String[] strings = { "llo" };
        assertThrows(IllegalArgumentException.class, () -> {
            List<String> result = org.example.Level3.Excercise4.findMostCommonStrings(strings);
        });
    }

    @Test
    public void case3() {
        String[] strings = { "hello", "world", "hello" };
        Assert.assertEquals(new ArrayList<>(List.of("hello", "hello")), org.example.Level3.Excercise4.findMostCommonStrings(strings));
    }

    @Test
    public void case4() {
        String[] strings = { "he", "wold", "rfba"};
        Assert.assertEquals(new ArrayList<>(List.of("")), org.example.Level3.Excercise4.findMostCommonStrings(strings));
    }
}
