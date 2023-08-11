package org.example.Level2;

public class Excercise2 {
    //Write a program that takes a list of strings as input and returns the longest word in the list.
    public static void main(String[] args) {
        String str = " Homm   Homommm    nay   toi di hoc";
        String arr [] = str.trim().split("\s+");
        System.out.println(arr);
        int maxLength = arr[0].length();
        String maxString = arr[0];
        for (String strr: arr) {
            if (strr.length() > maxLength) {
                maxLength = strr.length();
                maxString = strr;
            }
        }
        System.out.println("Chuỗi có độ dài lớn nhất : " + maxString);
    }
}
