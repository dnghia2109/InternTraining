package org.example.Level3;

public class Excercise4 {
    // Write a program that takes a list of strings as input and returns the two strings with the largest overlap of characters.
    // Tìm 2 chuỗi có nhiều số ký tự trùng nhau nhất
    // VD:["hello", "world", "foobar", "barfoo", "he", "llo"] 👉 ["foobar", "barfoo"]
    public static void findMostCommonStrings(String[] strings) {
        int maxCount = 0;
        String mostCommon1 = null;
        String mostCommon2 = null;

        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                int count = countCommonChars(strings[i], strings[j]);
                if (count > maxCount) {
                    maxCount = count;
                    mostCommon1 = strings[i];
                    mostCommon2 = strings[j];
                }
            }
        }

        if (maxCount > 0) {
            System.out.println("Hai chuỗi có nhiều ký tự trùng nhau nhất:");
            System.out.println(mostCommon1);
            System.out.println(mostCommon2);
            System.out.println("Số ký tự trùng nhau: " + maxCount);
        } else {
            System.out.println("Không có chuỗi nào có ký tự trùng nhau.");
        }
    }

    public static int countCommonChars(String str1, String str2) {
        int count = 0;
        for (char ch1 : str1.toCharArray()) {
            for (char ch2 : str2.toCharArray()) {
                if (ch1 == ch2) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] strings = { "hello", "world", "foobar", "barfoo", "he", "llo" };
        findMostCommonStrings(strings);
    }
}
