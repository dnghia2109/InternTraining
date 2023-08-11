package org.example.Level1;

import java.util.Scanner;

public class Excercise2 {
    //Write a program that takes a string as input and displays the length of the string.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("- Nhập vào chuỗi: ");
        String str = sc.next();
        System.out.println("- Độ dài chuỗi vừa nhập: " + str.length());
    }
}
