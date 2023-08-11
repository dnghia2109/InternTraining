package org.example.Level1;

import java.util.Arrays;

public class Excercise9 {
    // Write a program that takes a string as input and returns the number of words in the string.
    // ( tìm số từ trong một chuỗi, VD: “Hello world" => 2 )
    public static void main(String[] args) {
        String str = "    Hom    nay   toi di hoc";
        String arr [] = str.trim().split("\s+");
        System.out.printf(" Chuỗi `%s` có %d từ", str, arr.length);
    }


}
