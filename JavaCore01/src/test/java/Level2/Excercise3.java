package Level2;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

// Write a program that takes two strings as input and returns the longest common subsequence of the two strings.
// VD: đầu vào [“abcdef", “abczyzcdef”], Đầu ra: “cdef"
// Input: str1, str2 => Output: (String)chuỗi con chung dài nhất
public class Excercise3 {

    @Test
    public void case1() {
        String str1 = "";
        String str2 = "abc";
        assertEquals("", org.example.Level2.Excercise3.longestCommonSubstring(str1, str2));
    }

    @Test
    public void case7() {
        String str1 = "ab è ";
        String str2 = "è";
//        assertThrows(IllegalArgumentException.class, () -> {
//            String result = org.example.Level2.Excercise2.findLongestWord(str);
//        });
        assertEquals("è", org.example.Level2.Excercise3.longestCommonSubstring(str1, str2));
    }

    @Test
    public void case2() {
        String str1 = "";
        String str2 = "";
        assertEquals("", org.example.Level2.Excercise3.longestCommonSubstring(str1, str2));
    }

    @Test
    public void case3() {
        String str1 = "abc  def";
        String str2 = "abdaacdef";
        assertEquals("def", org.example.Level2.Excercise3.longestCommonSubstring(str1, str2));
    }

    @Test
    public void case4() {
        String str1 = "abc  def";
        String str2 = "abdaacdef";
        assertEquals("def", org.example.Level2.Excercise3.longestCommonSubstring1(str1, str2));
    }

    @Test
    public void case5() {
        String str1 = "";
        String str2 = "";
        assertEquals("", org.example.Level2.Excercise3.longestCommonSubstring1(str1, str2));
    }

    @Test
    public void case6() {
        String str1 = "abc def";
        String str2 = "";
        assertEquals("", org.example.Level2.Excercise3.longestCommonSubstring1(str1, str2));
    }

}
