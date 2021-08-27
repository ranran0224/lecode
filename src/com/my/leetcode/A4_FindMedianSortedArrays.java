package com.my.leetcode;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.Arrays;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/8/16
 */
public class A4_FindMedianSortedArrays {
    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 示例 3：
     * <p>
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     * 示例 4：
     * <p>
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     * 示例 5：
     * <p>
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     *  
     * <p>
     * 提示：
     * <p>
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     *  
     * <p>
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 自己解答 根据两个有序数组长度算出中位数index，两个数组从小到大循环直到中位数index，算出结果。
     * 时间复杂度低 O(m+n)
     * 缺点：内存占用空间大
     * @param nums1
     * @param nums2
     * @return
     */
    public static  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //遍历一遍两个数组，就可以得出结果
        //m+n=奇数，中位数index=floor[m+n/2]，下标向下取整
        //m+n=偶数，中位数=两个数/2，其中两个下标为（m+n）/-1，（m+n）/2
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalArrlength = length1 + length2;
        int index;
        boolean isOddNum;
        if (totalArrlength == 0) {
            return 0;
        } else if (totalArrlength % 2 == 0) {
            index = totalArrlength / 2;
            isOddNum = false;
        } else {
            index = Math.floorDiv(totalArrlength, 2);
            isOddNum = true;
        }
        double result = 0;
        int p1 = 0;
        int p2 = 0;
        int old = 0;
        int cur = 0;
        //指针大小比较,谁小移动指针
        while (p1 + p2 <= index) {
            boolean isP1 = false;
            //获取当前指针对应数值，将较小的放入cur存储，old是cur前一个
            if (p1 < length1 && p2 < length2) {
                if (nums1[p1] <= nums2[p2]) {
                    old = cur;
                    cur = nums1[p1];
                    isP1 = true;
                } else {
                    old = cur;
                    cur = nums2[p2];
                }
            } else if (p1 >= length1) {
                old = cur;
                cur = nums2[p2];
            } else if (p2 >= length2) {
                old = cur;
                cur = nums1[p1];
                isP1 = true;
            }
            //当放入个数=index 获取结果值
            if (p1 + p2 == index) {
                if (isOddNum) {
                    result = cur;
                } else {
                    result = (cur + old) / 2.0;
                }
                break;
            }
            if (isP1) {
                p1++;
            } else {
                p2++;
            }
        }
        return result;
    }

    /**
     * leetcode 官方解法一：数组合并（归并）
     * 时间复杂度：O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        double result = 0;
        //分别进行奇数偶数处理
        if(length % 2 != 0){
            result = getNum(nums1,nums2,length/2);
        }else{
            result = getNum(nums1,nums2,length/2-1)/2 + getNum(nums1,nums2,length/2)/2;
        }
        return result;
    }

    public double getNum(int[] nums1, int[] nums2, int k){
        int[] result = new int[nums1.length+nums2.length];
        int i = 0, j = 0;
        int cur = 0;
        while(i < nums1.length && j <nums2.length && cur <= k){
            if(nums1[i] < nums2[j]) result[cur++] = nums1[i++];
            else result[cur++] = nums2[j++];
        }
        while(i < nums1.length && cur <=k) result[cur++] = nums1[i++];
        while(j < nums2.length && cur <=k) result[cur++] = nums2[j++];
        return result[cur-1];
    }

    /**
     * 官方解法二：二分查找
     * 时间复杂度O(log(m+n))
     * 优点：时间复杂度最低
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        //选择长度较小的那个数组进行查找
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2,nums1);
        if(nums1.length == 0){
            if(nums2.length % 2 != 0) return nums2[length/2];
            else return (nums2[length/2-1] + nums2[length/2])/2.0;
        }
        ////初始化二分查找的边界
        int L_edge = 0, R_edge = nums1.length;
        int cur1 = 0,cur2 = 0;
        double result = 0;
        while(L_edge <= R_edge){
            cur1 = L_edge + (R_edge - L_edge)/2;
            cur2 = (length+1)/2 - cur1;
            //计算出L1，R1，L2，R2
            double L1 = cur1 == 0 ? Integer.MIN_VALUE:nums1[cur1-1];
            double R1 = cur1 == nums1.length ? Integer.MAX_VALUE:nums1[cur1];
            double L2 = cur2 == 0 ? Integer.MIN_VALUE:nums2[cur2-1];
            double R2 = cur2 == nums2.length ? Integer.MAX_VALUE:nums2[cur2];
            //二分查找，重新划定边界
            if(L1 > R2) R_edge = cur1-1;
            else if( L2 > R1) L_edge = cur1+1;
            else{
                //注意长度为奇数偶数的问题，奇数取中间的那个值，偶数则取两边的和的一半
                if(length % 2 != 0) result = Math.max(L1,L2);
                else result = (Math.max(L1,L2) + Math.min(R1,R2))/2.0;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2};
        int[] arr2 = {};
        System.out.println("arr1："+ Arrays.toString(arr1) +"\narr2："+Arrays.toString(arr2) +"\n数组中位数="+findMedianSortedArrays(arr1,arr2));
    }
}
