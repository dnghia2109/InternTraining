package org.example.Level1;

public class Excercise10 {
    // Write a program that takes a list of strings as input and returns the number of strings that contain the letter 'a'.
    public static void main(String[] args) {
        String listStr[] = {"Hom nay", "troi mua", "khong co sunny"};
        int count = 0;
        for (int i = 0; i < listStr.length; i++) {
            if (listStr[i].contains("a")) {
                count++;
            }
        }
        System.out.printf("Có %d chuỗi có xuất hiện kí tự 'a'", count);
    }
}
