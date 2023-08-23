package com.techlead.javaspring.javacore01;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/v1/java01/lv3")
public class ControllerLevel03 {
    @GetMapping("/ex1")
    public ResponseEntity<?> findTheSecondMinNum(@RequestParam List<Integer> nums) {
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min) {
                min2 = min;
                min = num;
            } else if (min2 > num && num > min) {
                min2 = num;
            }
        }
        return ResponseEntity.ok(min2);
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> maximumDifferenceBetweenAnyTwoElements(@RequestParam List<Integer> nums) {
        nums.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return ResponseEntity.ok(nums.get(nums.size() - 1) - nums.get(0));
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> findLongestIncreasingSubsequence(@RequestParam List<Integer> nums) {
        int n = nums.size();

        int[] dp = new int[n]; // Mảng dp để lưu độ dài của LIS tại mỗi vị trí
        dp[0] = 1;

        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1; // Mỗi phần tử tạo thành một LIS có độ dài 1
            for (int j = 0; j < i; j++) {
                if (nums.get(i) > nums.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return ResponseEntity.ok(maxLen);
    }

    @GetMapping("/ex4")
    public ResponseEntity<?> findMostCommonString(@RequestParam String[] strings) {
        return ResponseEntity.ok(CountCommonChar.findMostCommonStrings(strings));
    }

    @GetMapping("/ex5")
    public ResponseEntity<?> ex5(@RequestParam List<Integer> nums) {
        Collections.sort(nums);
        int ketQua = 1;
        for (int i = 0; i < nums.size() && nums.get(i) <= ketQua; i++) {
            ketQua += nums.get(i);
        }
        return ResponseEntity.ok(ketQua);
    }

    @GetMapping("/ex6")
    public ResponseEntity<?> findMedianNumBy2List(@RequestParam List<Integer> listNums,
                                                  @RequestParam List<Integer> listNums1) {
        double rs = 0;
        listNums.addAll(listNums1);
        Collections.sort(listNums);
        //System.out.println("- List nums: " + listNums);
        if (listNums.size() % 2 != 0) {
            rs = listNums.get(listNums.size()/2);
        } else {
            int midNumLeftSide = listNums.get(listNums.size()/2 - 1);
            int midNumRightSide = listNums.get(listNums.size()/2);
            rs = (double) (midNumLeftSide + midNumRightSide) / 2;
        }

        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex7")
    public ResponseEntity<?> findLongestPalindromeLength(@RequestParam String string) {
        //     xxabcxycbaxx
        // chan lay het
        // le >3  -1  = chan
        // + 1 lẻ => thành tâm đối xứng
        String newStr = string.replace(" ", "").toLowerCase();//xóa space, lowercase chuỗi
        // Số lần xuất hiện mỗi kí tự
        int countChar[] = new int[26];
        for (char c : newStr.toCharArray()) {
            countChar[c - 'a']++;
        }

        int palindrome = 0;
        boolean oddCount = false;
        for (int count : countChar) {
            if (count % 2 == 0) {
                palindrome += count;
            } else {
                palindrome += count - 1; // nếu kí tự xuất hiên lẻ thì - 1 -> chẵn
                oddCount = true;
            }
        }
        if (oddCount) {
            palindrome += 1; // nếu tồn tại ít nhất 1 kí tự lẻ thì cho phép +1 kí tự
        }

        return ResponseEntity.ok(palindrome);
    }

    @GetMapping("/ex10")
    public ResponseEntity<?> sortListByStringLenth(@RequestParam List<String> stringList) {
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        return ResponseEntity.ok(stringList);
    }
}
