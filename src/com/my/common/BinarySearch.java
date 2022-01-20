package com.my.common;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2022/1/20
 */
public class BinarySearch {
    //二分法查找法，arr数组中找到value
// 时间复杂度：O(log2 n)
    public static int binarySearch(int[] arr, int value) {

        int low = 0; // 开始位置
        int high = arr.length - 1; // 结束 位置

        while(low <= high) {
            int middle = (low + high) / 2;

            if (value == arr[middle]) {
                return middle; //返回查询到的索引位置
            }

            if (value > arr[middle]) {
                low = middle + 1;
            }

            if (value < arr[middle]) {
                high = middle - 1;
            }
        }
        //上面循环完毕，说明未找到，返回-1
        return -1;
    }
}
