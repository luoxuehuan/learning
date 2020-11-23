package com.dtwave.dipper.asset.util;

import java.text.DecimalFormat;

/**
 * 数学函数工具，计算方差 等
 *
 * @author hulb
 * @date 2020/3/2 上午10:17
 */
public class MathUtils {


    /**
     * 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n 或者s^2=[(x1-x)^2 +...(xn-x)^2]/(n-1)
     */
    public static double variance(double[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return dVar / m;
    }


    /**
     * 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n 或者s^2=[(x1-x)^2 +...(xn-x)^2]/(n-1)
     */
    public static double variance(long[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return dVar / m;
    }

    /**
     * 标准差σ=sqrt(s^2)
     *
     * @param x
     * @return
     */
    public static double standardDiviation(double[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        //reture Math.sqrt(dVar/(m-1));
        return Math.sqrt(dVar / m);
    }


    /**
     * 标准差σ=sqrt(s^2)
     *
     * @param x
     * @return
     */
    public static double standardDiviation(long[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        //reture Math.sqrt(dVar/(m-1));
        return Math.sqrt(dVar / m);
    }

    /**
     * 计算最大值
     *
     * @param arr
     * @return
     */
    public static long getMaxValue(long[] arr) {
        //将数组的第一个数分别赋值给 max 和 min
        long max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            //当前遍历的数如果比 max 大，就将该数赋值给 max
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 计算平均值
     *
     * @param arr
     * @return
     */
    public static double getAvgValue(long[] arr) {
        //将数组的第一个数分别赋值给 max 和 min
        double sum = 0L;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        double avg = sum/arr.length;

        System.out.println("数组的平均值是：" + avg);
        return avg;
    }

    public static String chufa(long a,int b) {
        //“0.00000000”确定精度
        DecimalFormat dF = new DecimalFormat("0.00");
        return dF.format((float)a/b);
    }

    /**
     * 计算最小值
     *
     * @param arr
     * @return
     */
    public static long getMinValue(long[] arr) {
        //将数组的第一个数分别赋值给 max 和 min
        long min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            //当前遍历的数如果比 min 小，就将该数赋值给 min
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("数组的最小值是：" + min);
        return min;
    }

    //计算总和




    public static void main(String[] args){
        long[] value = new long[7];
        value[0] = 0L;
        value[1] = 0L;
        value[2] = 0L;
        value[3] = 1L;
        value[4] = 1L;
        value[5] = 1L;
        value[6] = 1L;

        //计算方差
        variance(value);

        getAvgValue(value);


        getSumValue(value);
    }

    public static double getSumValue(long[] arr) {
        double sum = 0L;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        System.out.println("数组的总和是：" + sum);
        return sum;
    }
}
