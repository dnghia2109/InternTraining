package com.techlead.javaspring.javacore01;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/v1/java01/lv1")
public class ControllerLevel01 {
    @GetMapping("/ex1")
    public ResponseEntity<?> sumTwoNum(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok("a + b = " + (a + b));
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> lengthOfString(@RequestParam String str) {
        return ResponseEntity.ok("Length: " + str.length());
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> squareOfNum(@RequestParam int n) {
        return ResponseEntity.ok("n^2 = " + (n * n));
    }

    @GetMapping("/ex4")
    public ResponseEntity<?> maxNumInArray(@RequestParam List<Integer> nums) {
        int maxNumber = nums.get(0);
        for (int num : nums) {
            if (num > maxNumber) {
                maxNumber = num;
            }
        }
        return ResponseEntity.ok("Max number: " + maxNumber);
    }

    @GetMapping("/ex5")
    public ResponseEntity<?> shortestString(@RequestParam List<String> stringList) {
        String minStr = stringList.get(0);
        int minLength = stringList.get(0).length();
        for (String str : stringList) {
            int curLength = str.length();
            if (curLength < minLength) {
                minLength = curLength;
                minStr = str;
            }
        }
        return ResponseEntity.ok(minStr);
    }

    @GetMapping("/ex6")
    public ResponseEntity<?> sortListNumAsc(@RequestParam List<Integer> nums) {
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return ResponseEntity.ok(nums);
    }

    @GetMapping("/ex7")
    public ResponseEntity<?> sortListString(@RequestParam List<String> stringList) {
        Collections.sort(stringList);
        return ResponseEntity.ok(stringList);
    }

    @GetMapping("/ex8")
    public ResponseEntity<?> findMedianNumber(@RequestParam List<Integer> nums) {
        Collections.sort(nums);
        double medianNum = 0;
        if (nums.size() % 2 != 0) {
            medianNum = nums.get(nums.size()/2);
        } else {
            int midNumLeftSide = nums.get(nums.size()/2 - 1);
            int midNumRightSide = nums.get(nums.size()/2);
            medianNum = (double) (midNumLeftSide + midNumRightSide) / 2;
        }
        return ResponseEntity.ok(medianNum);
    }

    @GetMapping("/ex9")
    public ResponseEntity<?> countWords(@RequestParam String str) {
        String[] arrStr = str.trim().split("\s+");
        return ResponseEntity.ok(arrStr.length);
    }

    @GetMapping("/ex10")
    public ResponseEntity<?> countStringContainLetterA(@RequestParam List<String> stringList) {
        int count = 0;
        for (String str : stringList) {
            if (str.contains("a")) count += 1;
        }
        return ResponseEntity.ok(count);
    }

}
