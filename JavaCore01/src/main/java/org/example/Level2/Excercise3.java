package org.example.Level2;

public class Excercise3 {
    // Write a program that takes two strings as input and returns the longest common subsequence of the two strings.
    // VD: đầu vào [“abcdef", “abczyzcdef”], Đầu ra: “cdef"
    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "abdabczyzcdef";

        int x = str1.length();
        int y = str2.length();
        int[][] arr = new int[x + 1][y + 1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 1; i <= x ; i++) {
            for (int j = 1; j <= y; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    arr[i][j] = arr[i-1][j-1]+1;
                    if (arr[i][j] > maxLength){
                        maxLength = arr[i][j];
                        endIndex = i - 1;
                    }
                }else {
                    arr[i][j] = 0;
                }
            }
        }
        String ketQua = str1.substring(endIndex-maxLength+1,endIndex+1);
        System.out.println("Day con chung dai nhat : "+ ketQua);
    }
}
