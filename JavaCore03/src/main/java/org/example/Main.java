package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
     private static boolean kiemTraToanTu(char c) {
         return c == '+' || c == '-' || c == '*' || c == '/';
     }

     private static int uuTien(char c) {
         if (c == '(') {
             return 0;
         }
         if (c == '+' || c == '-') {
             return 1;
         }
         if (c == '*' || c == '/') {
             return 2;
         }
         return 3;
     }

     private static String chuyenTrungToSangHauTo(String trungTo) {
         StringBuilder hauTo = new StringBuilder();
         Stack<Character> stack = new Stack<>();
         for (char c : trungTo.toCharArray()) {
             if (Character.isDigit(c)) {
                 hauTo.append(c);
             } else if (kiemTraToanTu(c)) {
                 while (!stack.isEmpty() && uuTien(stack.peek()) >= uuTien(c)) {
                     hauTo.append(stack.pop());
                 }
                 stack.push(c);
             } else if (c == '(') {
                 stack.push(c);
             } else if (c == ')') {
                 while (!stack.isEmpty() && stack.peek() != '(') {
                     hauTo.append(stack.pop());
                 }
                 stack.pop();
             }
         }
         while (!stack.empty()) {
             hauTo.append(stack.pop());
         }
         return hauTo.toString();
     }

     private static double tinhGiaTri(String hauTo) {
         Stack<Double> stack = new Stack<>();
         for (char c : hauTo.toCharArray()) {
             if (Character.isDigit(c)) {
                 stack.push((double) (c - '0'));
             } else if (kiemTraToanTu(c)) {
                 double o1 = stack.pop();
                 double o2 = stack.pop();
                 double ketQua;
                 switch (c) {
                     case '+':
                         ketQua = o1 + o2;
                         break;
                     case '-':
                         ketQua = o2 - o1;
                         break;
                     case '*':
                         ketQua = o1 * o2;
                         break;
                     case '/':
                         ketQua = o2 / o1;
                         break;
                     case '%':
                         ketQua = o1 % o2;
                         break;
                     default:
                         throw new IllegalArgumentException("dau vao ko hop le");
                 }
                 stack.push(ketQua);
             }
         }
         return stack.pop();
     }

     public static void main(String[] args) {
         System.out.println("Nhap vao 1 chuoi");
         String x = new Scanner(System.in).nextLine();
         String hauTo = chuyenTrungToSangHauTo(x);
         System.out.println(hauTo);
         System.out.println("Ket qua: ");
         System.out.println(tinhGiaTri(hauTo));
     }

}