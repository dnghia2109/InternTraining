package org.example.Level2;

import java.util.Calendar;

public class Excercise2 {
    //Write a program that takes a list of strings as input and returns the longest word in the list.
    public static void main(String[] args) {
        long begin = Calendar.getInstance().getTimeInMillis();
        String str = " Homm   Homommm    nay   toi di hoc";
        System.out.println("Chuỗi có độ dài lớn nhất : " + findLongestWord(str));
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Executed Time: " + (end - begin));
    }

    public static String findLongestWord(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Chuỗi đầu vào rỗng.");
        }
        String[] arr = str.trim().split("\s+");
        int maxLength = arr[0].length();
        String maxString = arr[0];
        for (String strr: arr) {
            if (strr.length() > maxLength) {
                maxLength = strr.length();
                maxString = strr;
            }
        }
        return maxString;
    }
}
