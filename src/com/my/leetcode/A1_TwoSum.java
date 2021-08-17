package com.my.leetcode;


import com.my.util.OutUtil;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/5/27
 */
class A1_TwoSum {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

    /**
     *方法一：暴力枚举
     * 时间复杂度：O(N^2)，其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。（表示质疑：LeetCode中内存消耗和方法二相同，甚至还要大一点）
     *
     */

    public static int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
    /**
     *方法二：哈希表【最佳】
     * 思路：创建索引
            Map（数字X，下标）->快速查找到target-X
     * 时间复杂度：O(N)，其中 N是数组中的元素数量。对于每一个元素 x，我们可以 O(1)地寻找 target - x。
     * 空间复杂度：O(N)，其中 N是数组中的元素数量。主要为哈希表的开销。
     *
     */

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap();//Map（数字X，下标） 创建索引
        for (int i = 0; i < nums.length; ++i) {
            if(hashMap.keySet().contains(target-Integer.valueOf(nums[i]))){//寻找对应数
                return new int[]{hashMap.get(target-Integer.valueOf(nums[i])),i};//有。返回[对应数下标，本次下标]
            }
            hashMap.put(nums[i],i);//没有，放进本次循环[数字，索引]
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums1 = {3,3};
        int target1 = 6;

        int[] nums2 = {-10,-1,-18,-19};
        int target2 = -19;

        int[] nums3 = {0,4,3,0};
        int target3 = 0;
        System.out.println(Arrays.toString(twoSum2(nums3, target3)));
    }
}

