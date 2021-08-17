package com.my.leetcode;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/8/16
 */
public class A4_FindMedianSortedArrays {
    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 示例 3：
     *
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     * 示例 4：
     *
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     * 示例 5：
     *
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     *  
     *
     * 提示：
     *
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     *  
     *
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //遍历一遍两个数组，就可以得出结果
        //m+n=奇数，中位数index=floor[m+n/2]，下标向下取整
        //m+n=偶数，中位数=两个数/2，其中两个下标为（m+n）/-1，（m+n）/2
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalArrlength = length1+length2;
        int index ;
        boolean isOddNum;
        if(totalArrlength == 0){
            return 0;
        }else if(totalArrlength % 2 == 0){
            index = totalArrlength/2;
        }else {
            index = Math.floorDiv(totalArrlength,2);
            isOddNum = true;
        }
        int[] totalArrs;
        int tp = 0;
        int p1 = 0;
        int p2 = 0;
        int r1;
        //指针大小比较,谁小移动指针
        while (p1+p2<index){
            //如果指针到头，停止读取
            if(p1>=length1){

            }
            if(p1<length1){
                p2++;
            }
            if(nums1[p1]<nums2[p2]){

            }
            if(p1>length1){
                p1++;
            }
            if(p1<length1){
                p2++;
            }
        }
    }
}
