package org.example.Level1;

import java.util.Scanner;

public class Excercise1 {

    // Write a program that takes two numbers as inputs and displays their sum.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("- Nhập a: ");
        int a = sc.nextInt();
        System.out.println("- Nhập b: ");
        int b = sc.nextInt();
        System.out.println("==> Tổng a + b = " + (a + b));
    }
}
