package org.example.Level1;

public class Excercise5 {
    // Write a program that takes a list of strings as input and displays the shortest string in the list.
    public static void main(String[] args) {
        String listString[] = {"Today is today", "Hello Hi", "i'm Nghia"};
        int minLength = listString[0].length();
        int indexMinLength = 0;
        for (int i = 0; i < listString.length; i++) {
            if (listString[i].length() < minLength) {
                minLength = listString[i].length();
                indexMinLength = i;
            }
        }
        System.out.println(listString[indexMinLength]);
    }
}
