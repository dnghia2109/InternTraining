package org.example.Level1;

import java.util.Scanner;

public class Excercise3 {
    // Write a program that takes a number as input and displays its square
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("- Nhập số: ");
        int n = sc.nextInt();
        System.out.println("==> Bình phương của " + n + " = " + n*n);
    }
}
