package org.example.Level2;

public class Excercise3 {
    // Write a program that takes two strings as input and returns the longest common subsequence of the two strings.
    // VD: đầu vào [“abcdef", “abczyzcdef”], Đầu ra: “cdef"
    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "abdabczyzcdef";

//        int x = str1.length();
//        int y = str2.length();
//        int[][] arr = new int[x + 1][y + 1];
//        int maxLength = 0;
//        int endIndex = 0;
//
//        for (int i = 0; i < str1.length() ; i++) {
//            for (int j = 1; j <= y; j++) {
//                if (str1.charAt(i-1) == str2.charAt(j-1)){
//                    arr[i][j] = arr[i-1][j-1]+1;
//                    if (arr[i][j] > maxLength){
//                        maxLength = arr[i][j];
//                        endIndex = i - 1;
//                    }
//                }else {
//                    arr[i][j] = 0;
//                }
//            }
//        }
//        String ketQua = str1.substring(endIndex - maxLength + 1, endIndex + 1);
//        System.out.println("Day con chung dai nhat : "+ ketQua);


        // Tạo ma trận dp để lưu độ dài của chuỗi con chung tại mỗi vị trí.
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // Biến lưu độ dài chuỗi con chung dài nhất.
        int maxLength = 0;

        // Vị trí kết thúc của chuỗi con chung dài nhất.
        int endIndex = 0;

        // Duyệt qua từng ký tự trong chuỗi str1.
        for (int i = 1; i <= str1.length(); i++) {
            // Duyệt qua từng ký tự trong chuỗi str2.
            for (int j = 1; j <= str2.length(); j++) {
                // So sánh ký tự tại vị trí i-1 của str1 và j-1 của str2.
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

        // Trích xuất chuỗi con chung dài nhất từ str1 dựa vào độ dài và vị trí kết thúc.
        System.out.println(str1.substring(endIndex-maxLength, endIndex));
    }
}
