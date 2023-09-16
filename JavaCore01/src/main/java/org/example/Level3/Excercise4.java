package org.example.Level3;

import java.util.*;
import java.util.stream.Collectors;

public class Excercise4 {
    // Write a program that takes a list of strings as input and returns the two strings with the largest overlap of characters.
    // Tìm 2 chuỗi có nhiều số ký tự trùng nhau nhất
    // VD:["hello", "world", "foobar", "barfoo", "he", "llo"] 👉 ["foobar", "barfoo"]
    public static List<String> findMostCommonStrings(String[] strings) {
        if (strings.length < 2) {
            throw new IllegalArgumentException("Mảng cần ít nhất 2 phần tử");
        }
        int maxCountCommonChar = 0;
        String mostCommon1 = null;
        String mostCommon2 = null;
        //Set<String> result = new HashSet<>(); // DS các string có các kí tự giống nhau
        List<String> result = new ArrayList<>();
        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                int count = countCommonChars(strings[i], strings[j]);
                if (count >= maxCountCommonChar) {
                    maxCountCommonChar = count;
                    mostCommon1 = strings[i];
                    mostCommon2 = strings[j];
//                    result.clear();
//                    result.addAll(List.of(mostCommon1,mostCommon2));
                }
            }
        }
        if (maxCountCommonChar > 0) {
            result.addAll(List.of(mostCommon1, mostCommon2));
        } else {
            result.add("");
            System.out.println("Không có chuỗi nào có ký tự trùng nhau.");
        }

        return result;
//        List<String> temp = new ArrayList<>(result.stream().toList());
//        result.clear();
//
//        List<String> finalResult = new ArrayList<>();
//        for (int i = 0; i < temp.size() - 1; i++) {
//            for (int j = i + 1 ; j < temp.size(); j++) {
//                if ((countCommonChars(temp.get(i), temp.get(j)) == maxCountCommonChar) && !finalResult.contains(temp.get(i))){
//                    finalResult.add(temp.get(i));
//                    finalResult.add(temp.get(j));
//                }
//            }
//        }
//        finalResult = finalResult.stream().sorted(String::compareTo).toList();
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
        //String[] strings = { "hello", "world", "rfooba", "rbafoo", "he", "llo" };
        String[] strings = { "hello", "world", "rfooba", "rbafoo", "he", "llo" };
        //String[] strings = {"foobar", "foobar", "barfoo", "brafoo", };
        findMostCommonStrings(strings).forEach(System.out::println);

    }
}
