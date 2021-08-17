package com.my.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/8/12
 */
public class A3_LengthOfLongestSubstring {
    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 示例 4:
     * 输入: s = ""
     * 输出: 0
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
/**
 * 官方解答：滑动窗口 哈希表（hashset）
 * 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 rk
 *
 * 在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；
 *
 * 在枚举结束后，我们找到的最长的子串的长度即为答案。
 *
 * 判断重复字符
 *
 * 在上面的流程中，我们还需要使用一种数据结构来判断 是否有重复的字符，常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的 HashSet，Python 中的 set, JavaScript 中的 Set）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
 *
 * 至此，我们就完美解决了本题。*/


    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：O(N)，其中 NN 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
     * <p>
     * 空间复杂度：O(∣Σ∣)，其中Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0,128) 内的字符，即 ∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
     */
    //感觉右起点为rk+1更加好理解
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    //自己解答1:n^2:这个版本内循环变成where，存储用hashset就是官方版本，只对数据循环一遍
    public static int lengthOfLongestSubstring1(String s) {
        String resultStr = "";
        for (int j = 0; j < s.length(); j++) {
            String subStr = "";
            for (int i = j; i < s.length(); i++) {
                if (subStr.indexOf(s.charAt(i)) == -1) {
                    subStr += s.charAt(i);
                } else {
                    break;
                }
            }
            if (subStr.length() > resultStr.length()) {
                resultStr = subStr;
            }
        }
        return resultStr.length();
    }

    //【错误版本】自己解答2:，"ohomm"无法通过
    //这个思路是想通过切片获取，但是发现就算做出来可能也需要n！，效率比n^2还慢，这里只求长度，无需存储子字符串，比较完就ok
    public static int lengthOfLongestSubstring2(String s) {
        //1.空串 null
        if ("".equals(s) || s == null) {
            return 0;
        }
        //2.单一字符
        if (s.split(String.valueOf(s.charAt(0))).length == 0) {
            return 1;
        }
        //3.复杂情况
        List<String> charList = new ArrayList<>();
        String[] substrs = new String[0];
        HashSet<String> hashSet = new HashSet<>();
        //s根据每个字符进行切割
        for (int i = 0; i < s.length(); i++) {
            String curChar = String.valueOf(s.charAt(i));
            if (!charList.contains(curChar)) {
                charList.add(curChar);
                substrs = s.split(curChar);
                hashSet.addAll(Arrays.stream(substrs).map(substr -> curChar + substr).collect(Collectors.toSet()));
            }
        }
        HashSet<Object> removeHashSet = new HashSet<>();
        HashSet<Object> newSubStrHashSet = new HashSet<>();
        //子字符串判断每个字符切割无重复字符
        for (String str : hashSet) {
            boolean isSubstr = true;
            for (String ch : charList) {
                if (ch.equals(String.valueOf(str.charAt(0)))) {
                    continue;
                } else if (str.split(ch, -1).length > 2) {
                    isSubstr = false;
                    break;
                }
            }
            if (!isSubstr) {
                removeHashSet.add(str);
            }
        }
        //长度最长的返回
        hashSet.removeAll(removeHashSet);
        return hashSet.stream().max((str1, str2) -> {
            return str1.length() - str2.length();
        }).get().length();
    }

    //仿写哈希表解法
    public static int lengthOfLongestSubstring3(String s) {
        HashSet<Character> subStrHashSet = new HashSet<>();
        int r = 0;
        int maxLength = 0;
        int length = s.length();
        for (int i = 0; i < s.length(); i++) {
            //移动左节点
            if (i != 0) {
                subStrHashSet.remove(s.charAt(i-1));
            }
            //移动右节点，直至出现重复字段或者超过字符串长度;循环过程中，符合要求的放入哈希表
            while (r < length && !subStrHashSet.contains(s.charAt(r))) {
                subStrHashSet.add(s.charAt(r));
                ++r;//【这里易错，不要r++】
            }
            //每次滑动完，和目前最大长度比较，得到最大值
            maxLength = Math.max(maxLength,r-i);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";//3
        String s2 = "bbbbb";//1
        String s3 = "pwwkew";//3
        String s4 = " ";//1
        String s5 = "ohomm";//3

        System.out.println("输入字符串s："+s1+"\n长度="+lengthOfLongestSubstring3(s1));
        System.out.println("输入字符串s："+s2+"\n长度="+lengthOfLongestSubstring3(s2));
        System.out.println("输入字符串s："+s3+"\n长度="+lengthOfLongestSubstring3(s3));
        System.out.println("输入字符串s："+s4+"\n长度="+lengthOfLongestSubstring3(s4));
        System.out.println("输入字符串s：" + s5 + "\n长度=" + lengthOfLongestSubstring3(s5));
    }
}
