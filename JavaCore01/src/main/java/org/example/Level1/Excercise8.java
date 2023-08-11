package org.example.Level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Excercise8 {
    // Write a program that takes a list of numbers as input and returns the median of the numbers.
    // ( Tìm số trung vị ( số trung vị không phải số trung bình cộng ))

    public static void main(String[] args) {
        List<Integer> listNums = new ArrayList<>(List.of(1, 6,7,98,76,33,23,45,23,24,31));
        List<Integer> listNums1 = new ArrayList<>(List.of(6,7,98,76,33,23,45,23,24,31));
        findMedianNumber(listNums);
        findMedianNumber(listNums1);
    }

    private static void findMedianNumber(List<Integer> listNums) {
        Collections.sort(listNums);
        System.out.println("- List nums: " + listNums);
        if (listNums.size() % 2 != 0) {
            System.out.println(listNums.get(listNums.size()/2));
        } else {
            int midNumLeftSide = listNums.get(listNums.size()/2 - 1);
            int midNumRightSide = listNums.get(listNums.size()/2);
            System.out.println((double) (midNumLeftSide + midNumRightSide) / 2);
        }
    }

}
