/*  Author: 北辰
    日期: 11/10/2019
    题目要求:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
示例 1:
输入: 123
输出: 321
 示例 2:
输入: -123
输出: -321
示例 3:
输入: 120
输出: 21
注意:
假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
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

    /*要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法
      //pop operation
      pop = x%10
      x /= 10
      //push operation
      temp = rev*10 + pop
      rev = temp
      判断是否溢出的方法如下(假设rev整数):
      1.如果temp = rev*10 + pop导致溢出,一定有rev>=INTMAX/10
      2.若rev>INTMAX/10,则temp一定溢出
      3.若rev==INTMAX/10,则当pop>7时,temp一定溢出
      但事实本题不用判断rev==INTMAX/10的情况,因为若pop>7,则反转前的整数一定溢出
      时间复杂度:O(log(x)),x中大约有log(x)位数
      空间复杂度:O(1)
     */
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