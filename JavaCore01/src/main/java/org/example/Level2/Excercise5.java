package org.example.Level2;

import java.util.List;

public class Excercise5 {
    // Write a program that takes a list of integers as input
    // and returns the maximum sum of any contiguous subarray within the list.
    public static void main(String[] args) {
        int arrNumber [] = {1,2,-5,7,6,-4,9};
        int rs = Integer.MIN_VALUE;
        int curSum = 0;
        for (int element : arrNumber) {
            curSum = Math.max(element, element + curSum);
            rs = Math.max(rs, curSum);
        }
        System.out.println("Tong lon nhat cua cac phan tu lien ke: " + rs);
    }

    public static int sumContiguousSubarray(List<Integer> arrNumber) {
        if (arrNumber.isEmpty()) {
            throw new IllegalArgumentException("List đang rỗng");
        }
        int rs = Integer.MIN_VALUE;
        int curSum = 0;
        for (int element : arrNumber) {
            curSum = Math.max(element, element + curSum);
            rs = Math.max(rs, curSum);
        }
        return rs;
    }
}
