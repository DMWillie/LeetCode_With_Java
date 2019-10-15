/*  Author: 北辰
    日期: 15/10/2019
    题目要求:给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：
输入: "cbbd"
输出: "bb"
*/


public class LongestPalindromicSubstring {
    public static void main(String[] args){
        String str1 = "babad";
        String str2 = "cbbd";

        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(str1+"的最长回文子串为: "+solution.longestPalindrome(str1));
        System.out.println(str2+"的最长回文子串为: "+solution.longestPalindrome(str2));
    }

    public String longestPalindrome(String s){
        String maxStr = "";
        int mid = s.length()/2;
        while(mid>0){
            int d;
            for(d=0;mid-d>=0&&mid+d<=s.length();d++){
                if(s.charAt(mid-d)!=s.charAt(mid+d))
                    break;
            }
            if(2*d-1>maxStr.length())
                maxStr = s.substring(mid-d+1,mid+d);
            mid--;
        }
        mid = s.length()+1;
        while(mid<s.length()-1){
            int d;
            for(d=0;mid-d>=0&&mid+d<=s.length();d++){
                if(s.charAt(mid-d)!=s.charAt(mid+d))
                    break;
            }
            if(2*d-1>maxStr.length())
                maxStr = s.substring(mid-d+1,mid+d);
            mid++;
        }
        return maxStr;
    }
}
