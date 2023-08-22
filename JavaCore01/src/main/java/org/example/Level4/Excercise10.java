package org.example.Level4;

import java.util.*;

// Write a program that takes a list of strings as input and returns the two strings with the largest overlap of
// substrings, where the substrings must be at least k characters long (where k is a parameter to the function).
// Giống bài 2.3 nhưng thêm biến k là độ dài các ký tự trùng
public class Excercise10 {

    public static void main(String[] args) {
        int k = 3;
        List<String> stringList = new ArrayList<>(List.of("abcdf", "bcdegh", "cdefij", "cdefghi"));
        List<String> subStrings = new ArrayList<>(); //! danh sach chua cac chuoi con
        for (String str : stringList) {
            for (int i = 0; i <= str.length() - k; i++) {
                subStrings.add(str.substring(i, i + k)); //! cac chuoi con có do dai it nhat = k thi nem vao subStrings
            }
        }

        System.out.println(subStrings);
        Map<String, Integer> countMap = new HashMap<>(); //! dem so lan xuat hien cua tung chuoi con
        for (String subStr : subStrings) {
            countMap.put(subStr, countMap.getOrDefault(subStr, 0) + 1); //! nem vao map(chuoi con, so lan xuat hien)
        }

        String maxSubString = ""; //! tim chuoi con xuat hien nhieu nhat
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String substr = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount && substr.length() >= k) {
                maxSubString = substr;
                maxCount = count;
            }
        }
        System.out.println("Chuối con xuaats hiện nhiều nhất: " + maxSubString);
        List<String> ketQua = new ArrayList<>(); //! danh sach chua 2 chuoi con trung nhau lon nhat
        int count = 0;
        for (String string: stringList) {
            if (count == 2) {
                break;
            }
            if (string.contains(maxSubString)) {
                ketQua.add(string);
                count++;
            }

        }
        System.out.println(ketQua);
    }
}


