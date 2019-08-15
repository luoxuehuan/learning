package com.hulb.java.leetcode;

import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hulb
 * @date 2017/12/16 下午7:41
 */
public class OneTwo {
    public static void main(String[] arg) {
        System.out.println("hello world");
        int[] input = new int[4];
        input[0] = 2;
        input[1] = 5;
        input[2] = 5;
        input[3] = 6;

        int target = 10;

//        System.out.println( twoSum2(input, target)[0]);
//        System.out.println(twoSum(input, target)[1]);

        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        map.put("key",list);
        List<String> result = (List)map.get("key");
        System.out.println(result.get(0));


    }


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 两遍hash表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> dic = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            dic.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer other = target - nums[i];
            if(dic.containsKey(other) && dic.get(other) != i) {
                result[0] = i;
                result[1] = dic.get(other);
                return result;
            }
        }
        return result;
    }

    /**
     * 一遍hash表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}



