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
            if(inList == null && inList.isEmpty()){
                return;
            }
            this.val = inList.get(0);
            if(inList.size()>1){
                inList.remove(0);
                this.next = new ListNode(inList);
            }
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append( "\n{ListNode{val=").append(val).append(",next=");
            if(this.next!=null){
                sb.append(next.toString());
            }else {
                sb.append("{}");
            }
            sb.append("}");

            return sb.toString();
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newListNode = new ListNode();
        //递归出口
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            l1 = new ListNode(0);
        } else if (l2 == null) {
            l2 = new ListNode(0);
        }
        //1.相加
        int sumRes = l1.val + l2.val;
        //2.进位，存进位到l1的next的val里
        if (sumRes >= 10) {
            sumRes = sumRes - 10;
            if (l1.next != null) {
                l1.next.val += 1;
            } else {
                l1.next = new ListNode(1);
            }
        }
        //3.当前node.val存结果
        newListNode.val += sumRes;
        //4.递归
        newListNode.next = addTwoNumbers(l1.next, l2.next);
        return newListNode;
    }

    public void ss() {
        List<Integer> numList1 = new LinkedList(Arrays.asList(new Integer[]{9,9,9,9,9,9,9}));
        List<Integer> numList2 = new LinkedList(Arrays.asList(new Integer[]{9,9,9,9}));
        ListNode l1 = new ListNode(numList1);
        ListNode l2 = new ListNode(numList2);
        ListNode node = addTwoNumbers(l1, l2);
        System.out.println(node.toString());
    }

    public static void main(String[] args) {
        new A2_AddTwoNumbers().ss();
    }
}
