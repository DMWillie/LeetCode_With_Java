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
        System.out.println(str1+"的最长回文子串为: "+solution.longestPalindrome_9(str1));
        System.out.println(str2+"的最长回文子串为: "+solution.longestPalindrome_9(str2));
        System.out.println(str3+"的最长回文子串为: "+solution.longestPalindrome_9(str3));
        System.out.println(str4+"的最长回文子串为: "+solution.longestPalindrome_9(str4));


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
    //当字符串中存在某个子串的反向副本时,这种方法就会失效,例如"abcdacba"和其逆置"abcadcba"的最长公共子串为"abc"
    //但"abc"不是回文串
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

    //加上下标判断的最长公共子串法***,使用动态规划
    public String longestPalindrome_4(String s){
        /*时间复杂度:O(n^2)
          空间复杂度:O(n^2)
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
                    int beforeRevj = s.length()-1-j;                //beforeRevj表示reverse的末尾下标倒置前的下标
                    int beforeRevEndi = beforeRevj + arr[i][j] - 1;     //beforeRevEndi表示倒置前的末尾下标i的位置
                    if(beforeRevEndi==i){           //加上对应位置是否相等的判断
                        maxLen = arr[i][j];
                        maxEnd = i;         //记录结束位置i
                    }
                }
            }
        }
        return s.substring(maxEnd-maxLen+1,maxEnd+1);
    }

    //加上下标判断的最长公共子串法***,优化空间复杂度
    public String longestPalindrome_5(String s){
        /**我们每次更新arr[i][j]的时候其实只用到上一行的信息,例如arr[1][2]只用到第0行的信息,arr[2][1]只用到第1行的信息
         *完全用不到第0行的信息...所以我们只需要一个一维数组保存上一行的信息就行了。但有一个值得注意的地方是,j的下标不能从0开始
         * 因为arr[j]=arr[j-1]+1,若按0,1,2..的顺序,则我们更新完arr[1]之后,上一行的arr[1]的信息就被改变了,从而在更新本行的arr[2]的时候
         * 用到arr[1]会发生错误,所以j的下标得倒着来
         * 时间复杂度:O(n^2),空间复杂度:O(n)
         */
        if(s.equals(""))
            return "";
        String reverse = new StringBuffer(s).reverse().toString();
        int maxLen = 0;             //目前回文子串的最大长度
        int maxEnd = 0;             //目前最长回文子串的末尾下标位置
        int n = s.length();
        int[] arr = new int[n];         //辅助数组
        for(int i=0;i<n;i++){
            for(int j=n-1;j>=0;j--){            /**变化**/
                if(s.charAt(i)==reverse.charAt(j)){
                    if(i==0||j==0){
                        arr[j] = 1;
                    }else{
                        arr[j] = arr[j-1] + 1;
                    }
                }else{              /**变化,因为之前的arr[i][j]只用一行的信息,所以不用置0**/
                    arr[j] = 0;
                }
                //加上下标判断更新maxLen和maxEnd
                if(arr[j]>maxLen){
                    int beforeRevj = n - 1 - j;
                    if(beforeRevj + arr[j] - 1 == i){
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1,maxEnd+1);
    }

    //动态规划***,利用公式来表达回文子串
    public String longestPalindrome_6(String s){
        /**首先,我们定义P(i,j)=①true,如果s[i,j]是回文串②false,如果s[i,j]不是回文子串
         *那么P(i,j)=[P(i+1,j-1)&& s[i]==s[j]),但是i+1必须<=j-1,即j-i+1>=3(也就是字串的长度大于等于3
         *时才能用此公式),长度为1和2的子串得单独谈论.(1)长度为1,即P(i,i)=true(2)长度为2,P(i,j)=(s[i]==s[j])
         *所以我们需要初始化长度为1的回文子串,然后初始化长度为2的回文子串....
         * 时间复杂度:O(n^2),空间复杂度:O(n^2)
         */
        int maxLen = 0;             //记录当前回文子串的最大长度
        String maxStr = "";         //记录当前长度最大的回文子串
        int n = s.length();
        boolean[][] p = new boolean[n][n];      //初始化辅助数组
        for(int len=1;len<=n;len++){        //依次更新长度为len的子串
            for(int start=0;start<n;start++){   //start表示子串的开始下标
                int end = start+len-1; //end表示子串结束的位置
                if(end>=n)      //若结束位置索引超出范围,则跳出内层循环
                    break;
                p[start][end] = (len==1||len==2||p[start+1][end-1])
                        &&(s.charAt(start)==s.charAt(end));//len为1或2单独判断
                 if((p[start][end])&&(end-start+1>maxLen)){        //更新最大回文子串
                     maxLen = end-start+1;
                     maxStr = s.substring(start,end+1);
                 }
            }
        }
        return maxStr;
    }

    //动态规划***,优化空间复杂度
    public String longestPalindrome_7(String s){
        /**倒着遍历,求第i行我们只需要第i+1行的信息,并且j的话我们也只需要j-1的信息
         *时间复杂度:O(n^2),空间复杂度:O(n)
         */
        String res = "";
        int n = s.length();
        boolean[] p = new boolean[n];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=i;j--){
                int len = j-i+1;
                p[j] = (s.charAt(i)==s.charAt(j))&&(len<3||p[j-1]);
                if(p[j]&&len>res.length()){
                    res = s.substring(i,j+1);
                }
            }
        }
        return res;
    }

    //扩展中心算法***
    public String longestPalindrome_8(String s){
        /**由于回文串一定是左右对称的,所以我们可以从中心往两边扩展判断对应的字符是否相等
         * 由于存在奇数个字符串和偶数个字符串,所以总共有n + n-1 个中心
         * 时间复杂度:O(n^2),空间复杂度:O(1)
         */
        int start = 0,end = 0;      //回文串开始和结束的位置
        //外层循环寻找每个可能的中心
        for(int i=0;i<s.length();i++){
            int len1 = expandAroundCenter(s,i,i);       //奇数个字符串的中心
            int len2 = expandAroundCenter(s,i,i+1); //偶数个字符串的中心
            int len = Math.max(len1,len2);
            if(len>end-start+1){    //更新目前的最大回文子串
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start,end+1);
    }

    //扩展中心,返回回文子串的长度
    public int expandAroundCenter(String s,int left,int right){
        int L = left,R = right;
        while(L>=0&&R<s.length()&&(s.charAt(L)==s.charAt(R))){
            L--;
            R++;
        }
        return R-L-1;       //R-L+1-2=R-L-1
    }

    //Manacher's Algorithm 马拉车算法***
    public String longestPalindrome_9(String s){
        /**核心思想是充分利用回文串的对称性,利用边界情况和辅助字符将时间复杂度降到线性
         * 算法详解链接:
         * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
         */
        String T = preProcess(s);       //得到长度为奇数的字符串
        int n = T.length();
        int[] P = new int[n];           //数组P表示当前以每个字符为中心的最大回文子串的长度,初始化长度为0
        int C=0,R=0;                    //初始化字符串中心C和当前字符串中心的最大回文子串的半径
        for(int i=1;i<n-1;i++){
            int i_mirror = 2*C - i;     //以C为中心i的镜像字符的位置
            if(R>i){
                P[i] = Math.min(R-i,P[i_mirror]);       //防止P[i]的右边界已经超出了R
            }else{
                P[i] = 0;           //如果i==R,则令P[i]=0
            }

            //遇到其他的情况,则以i为中心利用中心扩展来求P[i]
            while(T.charAt(i+1+P[i])==T.charAt(i-1-P[i])){
                P[i]++;
            }
            //判断是否要更新R
            if(i+P[i]>R){
                C = i;
                R = C+P[i];
            }
        }

        //找出P的最大值
        int maxLen = 0;
        int currentCenterIndex = 0;
        for(int i=1;i<n-1;i++){
            if(P[i]>maxLen){
                maxLen = P[i];
                currentCenterIndex = i;
            }
        }
        int startIndex = (currentCenterIndex-maxLen)/2;   //求最开始的原字符串的下标
        return s.substring(startIndex,startIndex+maxLen);
    }

    public static String preProcess(String s){
        /**将字符预处理
         * 解决之前的中心扩展算法的奇数和偶数的判断方法:在每个字符间插入 "#"，并且为了使得扩展的过程中，到边界后自动结束，
         * 在两端分别插入 "^" 和 "$"，两个不可能在字符串中出现的字符，这样中心扩展的时候，判断两端字符是否相等的时候，
         * 如果到了边界就一定会不相等，从而出了循环。经过处理，字符串的长度永远都是奇数了
         */
        int n = s.length();
        if(n==0)
            return "^$";

        String ret = "^#";
        for(int i=0;i<n;i++)
            ret += s.charAt(i)+"#";
        ret += "$";
        return ret;
    }
}


