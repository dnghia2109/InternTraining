package org.example.Level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Excercise6 {
    // Write a program that takes two lists of integers as input and returns the median of the combined list.
    // Mở rộng của bài 1.8 nhưng với đầu vào là 2 mảng thay vì 1
    public static void main(String[] args) {
        List<Integer> listNums = new ArrayList<>(List.of(1, 6,7,98,76,33,23,45,23,24,31));
        List<Integer> listNums1 = new ArrayList<>(List.of(2,4,5,6));
        listNums.addAll(listNums1);
        System.out.println(listNums);
        findMedianNumber(listNums);

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
