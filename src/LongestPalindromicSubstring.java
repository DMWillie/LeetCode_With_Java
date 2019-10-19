/*  Author: 北辰
    日期: 15/10/2019
    题目要求:给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
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
        String str3 = "abdca";
        String str4 = "abacdfgdcaba";

        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(str1+"的最长回文子串为: "+solution.longestPalindrome_3(str1));
        System.out.println(str2+"的最长回文子串为: "+solution.longestPalindrome_3(str2));
        System.out.println(str3+"的最长回文子串为: "+solution.longestPalindrome_3(str3));
        System.out.println(str4+"的最长回文子串为: "+solution.longestPalindrome_3(str4));
    }

    public String longestPalindrome_1(String s){
        /*从中心向两边扩展并判断是不是回文子串,但是解法不对,应对"cbbd"这种的结果为"b"
        因为它是以中心字符判断两边是否为回文,而忽略了中心的字符和它旁边的字符也可能构成回文
         */
        String maxStr = "";
        int mid = s.length()/2;             //mid初始值为最中心字符
        while(mid>0){                   //中心字符向左寻找最长回文子串
            int d;
            for(d=0;mid-d>=0&&mid+d<=s.length();d++){
                if(s.charAt(mid-d)!=s.charAt(mid+d))
                    break;
            }
            if(2*d-1>maxStr.length())
                maxStr = s.substring(mid-d+1,mid+d);
            mid--;
        }
        mid = s.length()+1;             //mid变为右半部分的第一个字符
        while(mid<s.length()-1){        //中心字符向右寻找最长回文子串
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

    public String longestPalindrome_2(String s){
        /* 暴力法***,寻找所有可能的子字符串S(i,j),再判断它是否是回文串
        时间复杂度:O(n^3),两层for循环的时间复杂度为O(n^2),判断每个字符串是否为回文串的时间复杂度为O(n)
        空间复杂度:O(1)
         */
        String ans = "";            //ans表示最长的回文子串
        int max = 0;                //max表示最长回文子串的长度
        int n = s.length();         //n表示字符串s的长度
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                String test = s.substring(i,j);         //当前子串S(i,j),不包含S[j]
                if(isPalindrome(test)&&test.length()>max){
                    ans = test;             //更新当前最大回文子串
                    max = ans.length();
                }
            }
        }
        return ans;
    }
    //判断字符串是否为回文串
    public boolean isPalindrome(String s){
        for(int i=0;i<s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length()-i-1))
                return false;
        }
        return true;
    }

    //最长公共子串法***,将字符串s与s倒置后的s'进行比较,寻找最长公共子串(但这种解法不正确)
    public String longestPalindrome_3(String s){
        /*初始化一个二维数组arr[n][n](n代表s的长度),arr[i][j]表示字符串s从0-i
        与字符串s'从0-j的公共子串的子串,有递推关系arr[i][j]=①arr[i-1][j-1]+1,当s[i]=s'[j],且i!=0&j!=0
        ②1,当s[i]=s'[j],且i=0或j=0
        时间复杂度:O(n^2),两个for循环对s和s'所有位置元素进行遍历
        空间复杂度:O(n^2),需要借助n*n的辅助数组
         */
        if(s.equals(""))                //字符串为空
            return "";
        String reverse = new StringBuffer(s).reverse().toString();  //字符串倒置
        int n = s.length();
        int[][] arr = new int[n][n];    //初始化
        int maxLen = 0;         //到目前为止最大公共子串的长度
        int maxEnd = 0;         //到目前为止找到的最大公共子串的结束位置
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(i)==reverse.charAt(j)){
                    if(i==0||j==0){
                        arr[i][j] = 1;
                    }else{
                        arr[i][j] = arr[i-1][j-1]+1;
                    }
                }
                if(arr[i][j]>maxLen){
                    maxLen = arr[i][j];
                    maxEnd = i;         //记录结束位置i
                }
            }
        }
        return s.substring(maxEnd-maxLen+1,maxEnd+1);
    }
}


