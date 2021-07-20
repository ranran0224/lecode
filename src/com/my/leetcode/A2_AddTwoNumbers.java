package com.my.leetcode;

import com.my.util.OutUtil;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/7/12 19:47
 */
public class A2_AddTwoNumbers {
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(List<Integer> inList) {
            Collections.reverse(inList);
            OutUtil.printForList(inList);
            this.val = 100;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newListNode = new ListNode();
        while (l1.val == 0 && l2.val == 0) {
            //1.相加
            int sumRes = l1.val + l2.val;
            //2.进位
            boolean jinwei = false;
            if (sumRes > 10) {
                sumRes = sumRes - 10;
                jinwei = true;
            }
            //3.当前结构node存结果 存进位
            newListNode.val += sumRes;
            if(jinwei){
                newListNode.next = new ListNode(1,new ListNode(0));
            }
            //4.指向下一个节点
            l1 = l1.next;
            l2 = l2.next;
            newListNode = newListNode.next;

        }

        return newListNode;
    }

    public void ss() {
        List<Integer> numList1 = new LinkedList(Arrays.asList(new Integer[]{2, 4, 3}));
        List<Integer> numList2 = new LinkedList(Arrays.asList(new Integer[]{5,6,4}));
        new ListNode(numList1);
        addTwoNumbers(new ListNode(0),new ListNode(10,new ListNode()));
    }

    public static void main(String[] args) {
       new A2_AddTwoNumbers().ss();
    }
}
