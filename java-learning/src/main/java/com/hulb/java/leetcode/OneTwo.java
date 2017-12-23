package com.hulb.java.leetcode;

/**
 * @author hulb
 * @date 2017/12/16 下午7:41
 */
public class OneTwo {
    public static void main(String[] arg){
        System.out.println("hello world");
        int[] input = new int[4];
        input[0]=2;
        input[1]=5;
        input[2]=5;
        input[3]=6;

        int target = 10;
        System.out.println(twoSum(input,target)[0]);
        System.out.println(twoSum(input,target)[1]);
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }
}
