package org.example.Level3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Excercise1 {
    // Write a program that takes a list of numbers as input and returns the second smallest number in the list.
    public static void main(String[] args) {
//        int arrNumber[] = {1,-4,3,-4,5,6,7,9,8};
//        int min = Integer.MAX_VALUE;
//        int min2 = Integer.MAX_VALUE;
//        for (int curNum : arrNumber) {
//            if (min > curNum) {
//                min2 = min;
//                min = curNum;
//            } else if (min2 > curNum && curNum > min) {
//                min2 = curNum;
//            }
//        }
//        System.out.println(min);
//        System.out.println(min2);
        List<Double> arrNumber = new ArrayList<>(List.of(1.9, 1.9, 1.9));
        System.out.println(findSecondSmallestNum(arrNumber));
    }

    public static double findSecondSmallestNum(List<Double> arrNumber) {
        double min = Double.MAX_VALUE;
        double min2 = Double.MAX_VALUE;
        if (arrNumber.isEmpty()) {
            throw new IllegalArgumentException("List truyền vào rỗng");
        }

        for (int i = 0; i < arrNumber.size() - 1; i++) {
            if (Objects.equals(arrNumber.get(i), arrNumber.get(i + 1))) {
                return arrNumber.get(i);
            }
        }


        for (double curNum : arrNumber) {
            if (min > curNum) {
                min2 = min;
                min = curNum;
            } else if (min2 > curNum && curNum > min) {
                min2 = curNum;
            }
        }
        return min2;
    }
}
