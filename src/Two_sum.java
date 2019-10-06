/*  Author: 北辰
    日期: 06/10/2019
    题目要求:给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
            你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Two_sum {
    public static void main(String[] args){
        int[] nums = {2,3,3,1,8,7};
        int target = 6;
        Two_sum solution = new Two_sum();
        int[] result = solution.twoSum_3(nums,target);
        System.out.println("原数组中等于目标值的两个数的下标分别为: ");
        for(int e:result)
            System.out.print(e+"\t");
    }
    //方法一:暴力法,时间复杂度O(n^2),空间复杂度为O(1)
    public int[] twoSum_1(int[] nums, int target){
        int[] result = {0,0};
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i]+nums[j]==target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    //方法二:双指针法(需要先排序),时间复杂度为排序的时间复杂度O(n*log2n)
    //空间复杂度O(n),因为需要复制一份额外的数组
    public int[] twoSum_2(int[] nums,int target){
        int[] tmp = Arrays.copyOf(nums,nums.length);            //如果直接赋值传的是地址
        Arrays.sort(nums);              //将数组就地排序,按从小到大的顺序
        int res[] = {0,0};
        int i=0,j=nums.length-1;
        while(i<j){                     //从两头开始遍历数组
            if (nums[i] + nums[j] == target) {
                res[0] = nums[i];
                res[1] = nums[j];
                break;
            } else if (nums[i] + nums[j] < target)
                i++;
            else
                j--;
        }
        //最后寻找目标元素在原数组中的位置
        for(int k=0;k<tmp.length;k++){
            if(res[0]==tmp[k]) {
                res[0] = k;
                break;
            }
        }
        for(int k=0;k<tmp.length;k++){
            if(res[1]==tmp[k]&&k!=res[0]) {
                res[1] = k;
                break;
            }
        }
        return res;
    }
    //方法三***,一遍哈希表,利用哈希表存储元素和对应的下标,每次根据目标值寻找相应的元素,时间复杂度为O(1)
    //总时间复杂度为O(n),空间复杂度为O(n)
    public int[] twoSum_3(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}