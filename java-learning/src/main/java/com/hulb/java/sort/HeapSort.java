package com.hulb.java.sort;

import java.util.Arrays;

/**
 * @author hulb
 * @date 2018/6/12 下午7:25
 */
public class HeapSort {

        /**
         * 构建大顶堆
         */
        public static void adjustHeap(int[] a, int i, int len) {
            int temp, j;
            temp = a[i];
            // 沿关键字较大的孩子结点向下筛选
            for (j = 2 * i; j < len; j *= 2) {
                if (j < len && a[j] < a[j + 1]){
                    // j为关键字中较大记录的下标
                    ++j;
                }

                if (temp >= a[j]){
                    break;
                }
                a[i] = a[j];
                i = j;
            }
            a[i] = temp;
        }

        public static void heapSort(int[] a) {
            int i;
            // 构建一个大顶堆
            for (i = a.length / 2 - 1; i >= 0; i--) {
                adjustHeap(a, i, a.length - 1);
            }
            // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            for (i = a.length - 1; i >= 0; i--) {
                int temp = a[0];
                a[0] = a[i];
                a[i] = temp;
                // 将a中前i-1个记录重新调整为大顶堆
                adjustHeap(a, 0, i - 1);
            }
        }

        public static void main(String[] args) {
            int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
            heapSort(a);
            System.out.println(Arrays.toString(a));
        }


}



