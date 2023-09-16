package com.techlead.javaspring.javacore01;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CountCommonChar {
    public static List<String> findMostCommonStrings(String[] strings) {
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
        List<String> rs = new ArrayList<>();
        if (maxCount > 0) {
            rs.addAll(List.of(mostCommon1, mostCommon2));
        }

        return rs;

//        if (maxCount > 0) {
//            System.out.println("Hai chuỗi có nhiều ký tự trùng nhau nhất:");
//            System.out.println(mostCommon1);
//            System.out.println(mostCommon2);
//            System.out.println("Số ký tự trùng nhau: " + maxCount);
//        } else {
//            System.out.println("Không có chuỗi nào có ký tự trùng nhau.");
//        }
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
}
