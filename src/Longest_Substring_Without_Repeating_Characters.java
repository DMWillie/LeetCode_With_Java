/*  Author: 北辰
    日期: 07/10/2019
    题目要求:给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

import java.util.HashSet;
import java.util.Set;

public class Longest_Substring_Without_Repeating_Characters {

    //滑动窗口法,时间复杂度O(n^2)
    public int lengthOfLongestSubstring_1(String s) {
        int maxlen = 1;      //maxlen表示符合条件的最大子串的长度,初始值为1
        int i=0,j=1;        //i表示滑动窗口左边界下标,j表示滑动窗口右边界下标

        if(s.length()<maxlen)       //空串
            return 0;
        //非空串则利用滑动窗口更新最大值
        while(j<s.length()){        //循环结束条件
            boolean flag = true;    //判断s[i]-s[j]的元素是否全部不重复的标志
            for(int k=j-1;k>=i;k--){
                if(s.charAt(j)==s.charAt(k)){
                    i += 1;         //若有重复元素,则滑动窗口左边界向前1步
                    if(i==j)
                        j += 1;     //使滑动窗口至少有两个元素
                    flag = false;
                    break;              //若至少有一个重复元素,则不进行后续比较
                }
                //break若放在这里,则会陷入死循环
            }
            if(flag){            //若s[j]不等于s[i]-s[j-1]的元素
                j += 1;
                if(maxlen<j-i)
                    maxlen = j-i;       //更新滑动窗口的值
            }
        }
        return maxlen;
}

    //暴力法***,寻找所有可能的子串,再判断每个子串是否有重复的元素
    //时间复杂度:O(n^3),空间复杂度O(min(m,n)),我们需要 O(k)O(k) 的空间来检查子字符串中是否有重复字符，
    // 其中 k 表示 Set 的大小。而 Set 的大小取决于字符串 nn 的大小以及字符集/字母 m 的大小
    public int lengthOfLongestSubstring_2(String s){
        int n = s.length();
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                if(allUnique(s,i,j)) ans = Math.max(ans,j-i);
            }
        }
        return ans;
    }
    public boolean allUnique(String s,int start,int end){
        /*利用set集合判断给定的字符串是否有重复的元素
         */
        Set<Character> set = new HashSet<>();
        for(int i=start;i<end;i++){
            Character ch = s.charAt(i);
            if(set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    //主方法测试
    public static void main(String[] args){
        Longest_Substring_Without_Repeating_Characters solution =
                new Longest_Substring_Without_Repeating_Characters();
        //测试用例
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        System.out.println(str1+" 无重复字符的最长子串的长度为: "
                +solution.lengthOfLongestSubstring_1(str1));
        System.out.println(str2+" 无重复字符的最长子串的长度为: "
                +solution.lengthOfLongestSubstring_1(str2));
        System.out.println(str3+" 无重复字符的最长子串的长度为: "
                +solution.lengthOfLongestSubstring_1(str3));
    }
}
