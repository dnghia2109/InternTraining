package org.example.Level2;

public class Excercise3 {
    // Write a program that takes two strings as input and returns the longest common subsequence of the two strings.
    // VD: đầu vào [“abcdef", “abczyzcdef”], Đầu ra: “cdef"
//    public static void main(String[] args) {
//        String str1 = "";
//        String str2 = "";
//
//        // ma trận lưu độ dài của chuỗi con chung tại mỗi vị trí.
//        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
//
//        int maxLength = 0;
//
//        // Vị trí kết thúc của chuỗi con chung dài nhất.
//        int endIndex = 0;
//
//        for (int i = 1; i <= str1.length(); i++) {
//            for (int j = 1; j <= str2.length(); j++) {
//                // So sánh ký tự tại vị trí i-1 của str1 và j-1 của str2 -> nếu giống tăng độ dài chuỗi con.
//                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
//                    // Nếu ký tự giống nhau, tăng độ dài chuỗi con chung.
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//
//                    // Kiểm tra xem có chuỗi con mới dài hơn không.
//                    if (dp[i][j] > maxLength) {
//                        maxLength = dp[i][j];
//                        endIndex = i; // Lưu lại vị trí cuối cùng.
//                    }
//                }
//            }
//        }
//        System.out.println(str1.substring(endIndex - maxLength, endIndex));
//    }

    public static String longestCommonSubstring1(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        int maxLength = 0;

        // Vị trí kết thúc của chuỗi con chung dài nhất.
        int endIndex = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // So sánh ký tự tại vị trí i-1 của str1 và j-1 của str2 -> nếu giống tăng độ dài chuỗi con.
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // Nếu ký tự giống nhau, tăng độ dài chuỗi con chung.
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    // Kiểm tra xem có chuỗi con mới dài hơn không.
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i; // Lưu lại vị trí cuối cùng.
                    }
                }
            }
        }
        return str1.substring(endIndex - maxLength, endIndex);
    }


    public static String longestCommonSubstring(String str1, String str2) {
        int maxLength = 0;
        String longestSubstring = "";

        if (str1.isEmpty() || str2.isEmpty()) {
            longestSubstring = "";
        }

        for (int i = 0; i < str1.length(); i++) {
            for (int j = i + 1; j <= str1.length(); j++) {
                String substring = str1.substring(i, j);
                if (str2.contains(substring) && substring.length() > maxLength) {
                    maxLength = substring.length();
                    longestSubstring = substring;
                }
            }
        }

        return longestSubstring;
    }

    public static boolean validate(String string) {
        return string.isEmpty();
    }

    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "abdaacdef";
        String longestCommonSubstr = longestCommonSubstring(str1, str2);
        System.out.println("Chuỗi con chung dài nhất: " + longestCommonSubstr);
    }
}
