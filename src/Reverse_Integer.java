/*  Author: 北辰
    日期: 11/10/2019
    题目要求:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
输入: 123
输出: 321
 示例 2:
输入: -123
输出: -321
示例 3:
输入: 120
输出: 21
注意:
假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
请根据这个假设，如果反转后整数溢出那么就返回 0。
 */

public class Reverse_Integer {
    public static void main(String[] args){
        Reverse_Integer solution = new Reverse_Integer();
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;
        System.out.println(x1+"反转之后为: "+solution.reverse(x1));
        System.out.println(x2+"反转之后为: "+solution.reverse(x2));
        System.out.println(x3+"反转之后为: "+solution.reverse(x3));
    }
    public int reverse(int x) {
        int ans = 0;
        while(x!=0){
            if(ans>Integer.MAX_VALUE/10 || ans<Integer.MIN_VALUE/10){
                return 0;
            }
            ans = ans*10 + x%10;
            x = x/10;
        }
        return ans;
    }
}
