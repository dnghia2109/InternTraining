package com.techlead.javaspring.javacore01;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/java01/lv2")
public class ControllerLevel02 {
    @GetMapping("/ex1")
    public ResponseEntity<?> findTheSecondMaxNum(@RequestParam List<Integer> nums) {
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max2 = max;
                max = num;
            } else if (max2 < num && num < max) {
                max2 = num;
            }
        }

//        for (int i = 0; i < nums.size() ; i++) {
//            if (max < nums.get(i)){
//                max2 = max;
//                max = nums.get(i);
//            } else if (max2 < nums.get(i) &&nums.get(i) < max) {
//                max2 = nums.get(i);
//            }
//        }
        return ResponseEntity.ok(max2);
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> longestWord(@RequestParam String string) {
        String[] arr = string.trim().split("\s+");
        int maxLength = arr[0].length();
        String maxString = arr[0];
        for (String str: arr) {
            if (str.length() > maxLength) {
                maxLength = str.length();
                maxString = str;
            }
        }
        return ResponseEntity.ok(maxString);
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> findLongestCommonSubsequenceOfTheTwoStrings(@RequestParam String str1,
                                                                         @RequestParam String str2) {
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
        return ResponseEntity.ok(ketQua);
    }

    @GetMapping("/ex4")
    public ResponseEntity<?> sumOfTheNumbersThatAreDivisibleByBoth3And5 (@RequestParam List<Integer> nums) {
        int curSum = 0;
        for (int num : nums) {
            if ((num % 3 == 0) && (num %5 == 0)) {
                curSum += num;
            }
        }
        return ResponseEntity.ok(curSum);
    }

    @GetMapping("/ex5")
    public ResponseEntity<?> theMaximumSumOfAnyContiguousSubarrayWithinTheList (@RequestParam List<Integer> nums) {
        int rs = Integer.MIN_VALUE;
        int curSum = 0;
        for (int num : nums) {
            curSum = Math.max(num, num + curSum);
            rs = Math.max(rs, curSum);
        }
        return ResponseEntity.ok(curSum);
    }
}
