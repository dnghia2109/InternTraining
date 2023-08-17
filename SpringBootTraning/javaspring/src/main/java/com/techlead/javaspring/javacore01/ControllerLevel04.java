package com.techlead.javaspring.javacore01;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/java01/lv4")
public class ControllerLevel04 {
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

//    @GetMapping("/ex2")
//    public ResponseEntity<?> cau2(@RequestParam List<Integer> listNumber, @RequestParam int target) {
//
//    }
}
