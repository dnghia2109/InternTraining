package com.techlead.javaspring.javacore01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/java01/lv4")
public class ControllerLevel04 {

    @Autowired
    private Level4_Ex7Service level4_Ex7Service;

    @GetMapping("/ex1")
    public ResponseEntity<?> bubbleSort(@RequestParam int[] nums) {
        ArrayList<Integer> rs = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    count++;
                }
            }
        }

        for (int i:nums) {
            rs.add(i);
        }
        return ResponseEntity.ok(rs + "\n - Cần " + count + "lần sắp xếp.");
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> findLongestCommonStringLength(@RequestParam List<String> stringList) {
        //! tim chuoi ngan nhat
        String minStr = stringList.get(0);
        for (String str : stringList) {
            if (str.length() < minStr.length()) {
                minStr = str;
            }
        }
        //! tim do dai chuoi con chung dai nhat
        int maxLength = 0;
        for (int i = 0; i < minStr.length(); i++) {
            for (int j = i + 1; j <= minStr.length(); j++) {
                String subString = minStr.substring(i, j);
                boolean findString = true;
                for (String s : stringList) {
                    if (!s.contains(subString)) {
                        findString = false;
                        break;
                    }
                }
                if (findString) {
                    maxLength = Math.max(maxLength, subString.length());
                }
            }
        }
        return ResponseEntity.ok(maxLength);
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> cau2(@RequestParam List<Integer> listNumber, @RequestParam int target) {
        //List<Integer> listNumber = new ArrayList<>(List.of(1, 1, 2, 3, 4));
        //int target = 5;
        int n = listNumber.size();
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; //! Trường hợp target = 0, tồn tại một dãy rỗng có tổng bằng 0
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; //! Không bao gồm phần tử thứ i
                if (listNumber.get(i - 1) <= j) {
                    dp[i][j] += dp[i - 1][j - listNumber.get(i - 1)]; //! Bao gồm phần tử thứ i
                }
            }
        }
        return ResponseEntity.ok(dp[n][target]);
    }

    @GetMapping("/ex6")
    public ResponseEntity<?> cau6(@RequestParam int[] arr) {
        int x = arr.length;
        Arrays.sort(arr);
        int max3 = arr[x - 1] * arr[x - 2] * arr[x - 3];
        int max4 = arr[0] * arr[1] * arr[x - 1];
        int rs = Math.max(max3, max4);

        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex7")
    public ResponseEntity<?> cau7(@RequestParam String[] arr) {
        level4_Ex7Service.sapXep(arr);
        return ResponseEntity.ok(Arrays.toString(arr));
    }

    @GetMapping("/ex9")
    public ResponseEntity<?> cau9(@RequestParam List<Integer> listNumbers) {
        int count = 1; // dem so luong pt lien tiep
        int max = 1; // dem chieu dai day con tang dai nhat
        for (int i = 1; i < listNumbers.size(); i++) {
            if (listNumbers.get(i) - listNumbers.get(i - 1) <= 1) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(count, max);
        }

        return ResponseEntity.ok(max);
    }

    @GetMapping("/ex10")
    public ResponseEntity<?> cau10(@RequestParam List<String> stringList, @RequestParam int k) {
//        int k = 3;
//        List<String> stringList = new ArrayList<>(List.of("abcdf", "bcdegh", "cdefij", "cdefghi"));
        List<String> subStrings = new ArrayList<>(); // DS các substring
        for (String str : stringList) {
            for (int i = 0; i <= str.length() - k; i++) {
                subStrings.add(str.substring(i, i + k)); // add các substring vào list
            }
        }

//        System.out.println(subStrings);

        Map<String, Integer> countMap = new HashMap<>(); // dem so lan xuat hien cua tung chuoi con
        for (String subStr : subStrings) {
            countMap.put(subStr, countMap.getOrDefault(subStr, 0) + 1);
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
        System.out.println("Chuỗi con xuất hiện nhiều nhất: " + maxSubString);
        List<String> rs = new ArrayList<>();
        int count = 0;
        for (String string: stringList) {
            if (count == 2) {
                break;
            }
            if (string.contains(maxSubString)) {
                rs.add(string);
                count++;
            }

        }
        return ResponseEntity.ok(rs);
    }
}
