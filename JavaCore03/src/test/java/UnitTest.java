import org.example.PostfixV1;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class UnitTest {
    PostfixV1 postfixV1 = new PostfixV1();

    // Ver1
    @Test
    public void testWithInputIsNull() {
        String infix = "";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix);
        });
    }


    @Test
    public void testWithInvalidBracket() {
        String infix = "(6-(9-8)+1";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix);
        });

        String infix1 = "(6)-)9-8)+1";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix1);
        });

        String infix2 = "((6-(9-8)+1";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix2);
        });
    }

    @Test
    public void testMultipleOperator() {
        String infix = "(6-+(9-8)+1";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix);
        });

        String infix1 = "9+(7-8)*/1";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix1);
        });

        String infix2 = "1+2*(6**9)";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = postfixV1.chuyenTrungToSangHauTo(infix2);
        });
    }

    @Test
    public void testInputIsOnlyAlphabet() {
        String infix = "(a+b)*c-d";
        String expectedResult = "ab+c*d-";
        Assert.assertEquals(expectedResult, postfixV1.chuyenTrungToSangHauTo(infix));

        String infix1 = "(a+b-c*d)*c-d";
        String expectedResult1 = "ab+cd*-c*d-";
        Assert.assertEquals(expectedResult1, postfixV1.chuyenTrungToSangHauTo(infix1));

        String infix2 = "(a*b-c)/e-d";
        String expectedResult2 = "ab*c-e/d-";
        Assert.assertEquals(expectedResult2, postfixV1.chuyenTrungToSangHauTo(infix2));

        String infix3 = "(a*b+f)-c/d";
        String expectedResult3 = "ab*f+cd/-";
        Assert.assertEquals(expectedResult3, postfixV1.chuyenTrungToSangHauTo(infix3));
    }

    @Test
    public void testInputOnlyNumber() {
        String infix = "(6+9)*3-2";
        String expectedResult = "69+3*2-";
        Assert.assertEquals(expectedResult, postfixV1.chuyenTrungToSangHauTo(infix));

        String infix1 = "(5-9*2)*3-2";
        String expectedResult1 = "592*-3*2-";
        Assert.assertEquals(expectedResult1, postfixV1.chuyenTrungToSangHauTo(infix1));

        String infix2 = "(5-9*2)*3-2";
        String expectedResult2  = "592*-3*2-";
        Assert.assertEquals(expectedResult2, postfixV1.chuyenTrungToSangHauTo(infix2));
    }

    // Test trường hợp số có 1 chữ số
    @Test
    public void testGetValuePostfixWithInputHasOnlySingleDigitNumber() {
        String infix = "(6+9)*3-2";
        String expectedResult = "69+3*2-";
        Assert.assertEquals(expectedResult, postfixV1.chuyenTrungToSangHauTo(infix));
        Assert.assertEquals(43, postfixV1.evaluatePostfixV1(expectedResult), 0);

        String infix1 = "(1+8-9*2)/(3-2*2)";
        String expectedResult1 = "18+92*-322*-/";
        Assert.assertEquals(expectedResult1, postfixV1.chuyenTrungToSangHauTo(infix1));
        Assert.assertEquals(9, postfixV1.evaluatePostfixV1(expectedResult1), 0);

        String infix2 = "(6+9)*3-2";
        String expectedResult2 = "69+3*2-";
        Assert.assertEquals(expectedResult2, postfixV1.chuyenTrungToSangHauTo(infix2));
        Assert.assertEquals(43, postfixV1.evaluatePostfixV1(expectedResult2), 0);

        String infix3 = "(6+9)*3-2";
        String expectedResult3 = "69+3*2-";
        Assert.assertEquals(expectedResult3, postfixV1.chuyenTrungToSangHauTo(infix3));
        Assert.assertEquals(43, postfixV1.evaluatePostfixV1(expectedResult3), 0);
    }

    // Test trường hợp số có nhiều chữ số
//    @Test
//    public void testGetValuePostfixWithInputHasOnlyMultiDigitNumber() {
//        String infix1 = "(15+8-9*2)/(3-2*2)";
//        String expectedResult1 = "158+92*-322*-/";
//        Assert.assertEquals(expectedResult1, postfixV1.chuyenTrungToSangHauTo(infix1));
//        Assert.assertEquals(-5, postfixV1.evaluatePostfixV1(expectedResult1), 0);
//
//        String infix2 = "(19+ 1)*2-5";
//        String expectedResult2 = "191+2*5-";
//        Assert.assertEquals(expectedResult2, postfixV1.chuyenTrungToSangHauTo(infix2));
//        Assert.assertEquals(35, postfixV1.evaluatePostfixV1(expectedResult2), 0);
//    }

// ================================================================================================================================

    // Ver2: Cập nhật lại định dan của postfix (thêm space giữa các số và toán tử), Tính được cc phép tính có số có nhiều chữ số
    @Test
    public void testGetValuePostfixWithInputHasOnlySingleDigitNumberV2() {
        String infix = "(6+9)*3-2";
        String expectedResult = "6 9 + 3 * 2 -";
        Assert.assertEquals(expectedResult, postfixV1.infixToPostfixV2(infix));
        Assert.assertEquals(43, postfixV1.evaluatePostfixV2(expectedResult), 0);

        String infix1 = "(1+8-9*2)/(3-2*2)";
        String expectedResult1 = "1 8 + 9 2 * - 3 2 2 * - /";
        Assert.assertEquals(expectedResult1, postfixV1.infixToPostfixV2(infix1));
        Assert.assertEquals(9, postfixV1.evaluatePostfixV2(expectedResult1), 0);
    }

    @Test
    public void testGetValuePostfixWithInputHasOnlyMultiDigitNumberV2() {
        String infix1 = "(15+8-9*2)/(3-2*2)";
        String expectedResult1 = "15 8 + 9 2 * - 3 2 2 * - /";
        Assert.assertEquals(expectedResult1, postfixV1.infixToPostfixV2(infix1));
        Assert.assertEquals(-5, postfixV1.evaluatePostfixV2(expectedResult1), 0);

        String infix2 = "(19+ 1)*2-5";
        String expectedResult2 = "19 1 + 2 * 5 -";
        Assert.assertEquals(expectedResult2, postfixV1.infixToPostfixV2(infix2));
        Assert.assertEquals(35, postfixV1.evaluatePostfixV2(expectedResult2), 0);

        String infix3 = "(192+ 1)*2-5";
        String expectedResult3 = "192 1 + 2 * 5 -";
        Assert.assertEquals(expectedResult3, postfixV1.infixToPostfixV2(infix3));
        Assert.assertEquals(381, postfixV1.evaluatePostfixV2(expectedResult3), 0);
    }
}
