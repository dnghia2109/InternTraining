package org.example.Level4;

import java.util.*;

// Write a program that takes a list of strings as input and returns the two strings with the largest overlap of
// substrings, where the substrings must be at least k characters long (where k is a parameter to the function).
// Giống bài 2.3 nhưng thêm biến k là độ dài các ký tự trùng
public class Excercise10 {
//    public static String findMaxOverlap(List<String> strings, int k) {
//        String result = "";
//        int maxOverlap = 0;
//
//        for (int i = 0; i < strings.size(); i++) {
//            for (int j = i + 1; j < strings.size(); j++) {
//                String s1 = strings.get(i);
//                String s2 = strings.get(j);
//
//                int overlap = findOverlap(s1, s2, k);
//                if (overlap > maxOverlap) {
//                    maxOverlap = overlap;
//                    result = "Strings: \"" + s1 + "\" and \"" + s2 + "\", Overlap: " + overlap;
//                }
//            }
//        }
//        return result;
//    }
//
//    public static int findOverlap(String s1, String s2, int k) {
//        int maxOverlap = 0;
//        int n = s1.length();
//
//        for (int i = 0; i < n - k + 1; i++) {
//            String substr = s1.substring(i, i + k);
//            if (s2.contains(substr)) {
//                int overlap = k + findOverlap(s1.substring(i + k), s2.substring(s2.indexOf(substr) + k), k);
//                maxOverlap = Math.max(maxOverlap, overlap);
//            }
//        }
//
//        return maxOverlap;
//    }
//
//    public static void main(String[] args) {
//        List<String> strings = new ArrayList<>(List.of("abcdef", "bcdegh", "cdefij", "defghi"));
//        int k = 3;
//        String result = findMaxOverlap(strings, k);
//        System.out.println("Maximum overlap: " + result);
//    }


//    public static void main(String[] args) {
//        int k = 5;
//        List<String> stringList = new ArrayList<>(List.of("abcdef", "bcdegh", "cdefij", "defghi"));
//        List<String> subStrings = new ArrayList<>(); //! danh sach chua cac chuoi con
//        for (String str : stringList) {
//            for (int i = 0; i < str.length() - k; i++) {
//                subStrings.add(str.substring(i, i + k)); //! cac chuoi con coo do dai it nhat = k thi nem vao subStrings
//            }
//        }
//        Map<String, Integer> countMap = new HashMap<>(); //! dem so lan xuat hien cua tung chuoi con
//        for (String subStr : subStrings) {
//            countMap.put(subStr, countMap.getOrDefault(subStr, 0) + 1); //! nem vao map(chuoi con, so lan xuat hien)
//        }
//        String maxSubString = ""; //! tim chuoi con xuat hien nhieu nhat
//        int maxCount = 0;
//        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//            String substr = entry.getKey();
//            int count = entry.getValue();
//            if (count > maxCount && substr.length() >= k) {
//                maxSubString = substr;
//                maxCount = count;
//            }
//        }
//        List<String> ketQua = new ArrayList<>(); //! danh sach chua 2 chuoi con trung nhau lon nhat
//        ketQua.add(maxSubString);
//        ketQua.add(maxSubString);
//        System.out.println(ketQua);
//    }
public static void main(String[] args) {
    List<String> strings = Arrays.asList("abcde", "cdefg", "efghij");
    int k = 1;
    String[] result = findLargestOverlap(strings, k);

    if (result != null) {
        System.out.println("Strings with the largest overlap:");
        System.out.println(result[0]);
        System.out.println(result[1]);
    } else {
        System.out.println("No strings with overlap found.");
    }
}

    public static String[] findLargestOverlap(List<String> strings, int k) {
        Map<String, List<String>> substringMap = new HashMap<>();
        int maxOverlap = 0;
        String[] selectedStrings = new String[2];

        for (String str : strings) {
            for (int i = 0; i <= str.length() - k; i++) {
                String substring = str.substring(i, i + k);
                substringMap.putIfAbsent(substring, new ArrayList<>());
                substringMap.get(substring).add(str);
            }
        }

        for (List<String> stringList : substringMap.values()) {
            if (stringList.size() > 1) {
                for (int i = 0; i < stringList.size(); i++) {
                    for (int j = i + 1; j < stringList.size(); j++) {
                        String str1 = stringList.get(i);
                        String str2 = stringList.get(j);
                        int overlap = k;
                        while (overlap < Math.min(str1.length(), str2.length()) &&
                                str1.charAt(overlap) == str2.charAt(overlap)) {
                            overlap++;
                        }
                        if (overlap > maxOverlap) {
                            maxOverlap = overlap;
                            selectedStrings[0] = str1;
                            selectedStrings[1] = str2;
                        }
                    }
                }
            }
        }

        return selectedStrings[0] != null && selectedStrings[1] != null ? selectedStrings : null;
    }


}


