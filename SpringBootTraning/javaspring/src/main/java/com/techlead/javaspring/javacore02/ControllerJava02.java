package com.techlead.javaspring.javacore02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/java02")
@Slf4j
public class ControllerJava02 {

    @GetMapping("/ex1")
    public ResponseEntity<?> demoArrayList(@RequestParam List<Integer> list) {
        list.add(8);
        int sum = 0;
        for (int num: list) {
            sum += num;
        }
        list = list.stream().sorted((o1, o2)->o1.compareTo(o2)).collect(Collectors.toList());
        log.info("Tổng của list - {}", sum);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> demoHashSet(@RequestParam Set<String> stringSet) {
        stringSet.addAll(Set.of("Vietnam", "Japan", "USA", "France", "China"));
        stringSet.add("China");
        for (String nation : stringSet) {
            System.out.println(nation);
        }

        stringSet.remove("China");
        System.out.println(stringSet.size());
        return ResponseEntity.ok(" ");
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> cau3(@RequestParam Set<String> stringSet) {

        return ResponseEntity.ok("");
    }


    @GetMapping("/ex4")
    public ResponseEntity<?> sortList(@RequestParam List<Integer> list) {
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });


        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return ResponseEntity.ok(list);
    }

    @GetMapping("ex6")
    public ResponseEntity<?> maxProfit(@RequestParam int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return ResponseEntity.ok(maxProfit);
    }

}
