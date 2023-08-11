package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Bài tập 5: Given an array of integers nums and an integer target,
//return indices of the two numbers such that they add up to target.
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//You can return the answer in any order.
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
public class Excercise5 {
    public static void main(String[] args){
        int[] nums = {2,7,11,15};
        int target = 9;

        Map<Integer, Integer> result = twoSum(nums, target);
        for (Map.Entry<Integer,Integer> i: result.entrySet()){
            System.out.println("[" + i.getKey() + "," + i.getValue() + "]");
        }

    }

    public static Map<Integer, Integer> twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                result.put(i, map.get(complement));
            }
            map.put(nums[i], i);
        }

        return result;
    }
}
