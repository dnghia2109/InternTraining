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
// solution: giả sử nums1 + nums2 = target
// ==> duyệt for cho nums, tại nums[i] sẽ tính đc nums2 = target - nums[i] ==> để yc not use the same element twice ==> dùng map
// sau mỗi vòng lặp nếu nums2 chưa tồn tại trong map thì put, else trả rs = [value của nums1, vị trí i]
public class Excercise5 {
    public static void main(String[] args){
        int[] nums = {2,7,11,15};
        int target = 9;

        int[] rs = twoSum(nums, target);
        System.out.println(rs[0] + "-" + rs[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] rs = new int[0];

        for (int i = 0; i < nums.length; i++) {
            int nums2 = target - nums[i];
            if (map.containsKey(nums2)) {
                rs = new int[] { map.get(nums2), i };
            }
            map.put(nums[i], i);
        }
        return rs;
    }
}
