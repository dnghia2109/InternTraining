package Level3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Excercise10 {
    @Test
    public void testWithInputIsNull() {
        List<String> stringList = new ArrayList<>();
        Assert.assertEquals(new ArrayList<>(), org.example.Level3.Excercise10.sortByNumberOfDistinctCharacter(stringList));
    }

    @Test
    public void testWithNormalList() {
        List<String> stringList = new ArrayList<>(List.of("apple", "banana", "orange", "kiwi", "strawberry"));
        Assert.assertEquals(new ArrayList<>(List.of("kiwi", "banana", "apple", "orange", "strawberry")), org.example.Level3.Excercise10.sortByNumberOfDistinctCharacter(stringList));

        List<String> stringList1 = new ArrayList<>(List.of("apple", "applee", "hi", "nothing", "everyday"));
        List<String> expectedResult1 = new ArrayList<>(List.of("hi", "apple", "applee", "nothing", "everyday"));
        Assert.assertEquals(expectedResult1, org.example.Level3.Excercise10.sortByNumberOfDistinctCharacter(stringList1));

        List<String> stringList2 = new ArrayList<>(List.of("abcdf", "aacdf", "aaaaf", "aaadf"));
        List<String> expectedResult2 = new ArrayList<>(List.of("aaaaf", "aaadf", "aacdf", "abcdf"));
        Assert.assertEquals(expectedResult2, org.example.Level3.Excercise10.sortByNumberOfDistinctCharacter(stringList2));


        List<String> stringList3 = new ArrayList<>(List.of("1234", "5678988888", "44331299", "56789"));
        List<String> expectedResult3 = new ArrayList<>(List.of("1234", "56789", "44331299", "5678988888"));
        Assert.assertEquals(expectedResult3, org.example.Level3.Excercise10.sortByNumberOfDistinctCharacter(stringList3));

    }


}
