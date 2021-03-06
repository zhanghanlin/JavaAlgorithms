package com.demo.util.sort;

import com.demo.util.Utils;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author zhanghanlin
 */
public class RadixSort {

    public RadixSort() {
    }

    /**
     * 基数
     */
    public static final int RADIX = 10;

    /**
     * 基数排序 - 基于计数排序
     * 伪代码
     *
     * @param t
     */
    public static void exampleSort(Integer[] t) {
        int length = t.length;
        //用于暂存元素
        Integer[] temp = new Integer[length];
        //用于计数排序
        Integer[] count = new Integer[length];
        //最大位数
        int numberOfDigits = findManxNumber(t);
        //初始化倍数基数为1
        int divide = 1;
        for (int i = 0; i < numberOfDigits; i++) {
            System.arraycopy(t, 0, temp, 0, length);
            Arrays.fill(count, 0);
            for (int j = 0; j < length; j++) {
                count[(temp[j] / divide) % RADIX]++;
            }
            for (int j = 1; j < RADIX; j++) {
                count[j] += count[j - 1];
            }
            for (int j = length - 1; j >= 0; j--) {
                int tempKey = (temp[j] / divide) % RADIX;
                t[--count[tempKey]] = temp[j];
            }
            divide *= RADIX;
        }
    }

    /**
     * 10 for base 10 numbers
     */
    private static final int NUMBER_OF_BUCKETS = 10;

    public static Integer[] sort(Integer[] t) {
        int[][] buckets = new int[NUMBER_OF_BUCKETS][10];
        for (int i = 0; i < buckets.length; i++) {
            //每个子数组第一位记录该数组内的元素个数+1
            buckets[i][0] = 1;
        }
        //最大位数
        int numberOfDigits = findManxNumber(t);
        //除数
        int divisor = 1;
        for (int i = 0; i < numberOfDigits; i++) {
            //第i位divisor的余数
            int digit = 0;
            //根据余数将元素放入对应的数组中
            for (int j : t) {
                digit = getDigit(j, divisor);
                buckets[digit] = add(j, buckets[digit]);
            }
            int index = 0;
            for (int j = 0; j < NUMBER_OF_BUCKETS; j++) {
                int[] bucket = buckets[j];
                int size = bucket[0];
                for (int k = 1; k < size; k++) {
                    t[index++] = bucket[k];
                }
                buckets[j][0] = 1;
            }
            divisor *= 10;
        }
        return t;
    }

    /**
     * 将子数组放入指定数组内
     *
     * @param integer
     * @param bucket
     * @return
     */
    private static int[] add(int integer, int[] bucket) {
        // size is stored in first element
        int size = bucket[0];
        int length = bucket.length;
        if (size >= length) {
            bucket = Arrays.copyOf(bucket, ((length * 3) / 2) + 1);
        }
        bucket[size] = integer;
        bucket[0] = ++size;
        return bucket;
    }

    /**
     * 获取第divisor位10的余数
     *
     * @param integer
     * @param divisor
     * @return
     */
    private static int getDigit(int integer, int divisor) {
        return (integer / divisor) % 10;
    }

    /**
     * 获得log10最大的Val
     *
     * @param t
     * @return
     */
    private static int findManxNumber(Integer[] t) {
        int maxVal = Integer.MIN_VALUE;
        int temp;
        for (int i : t) {
            temp = (int) Math.log10(i) + 1;
            if (temp > maxVal) {
                maxVal = temp;
            }
        }
        return maxVal;
    }

    public static void main(String[] args) {
        Integer[] a = Utils.random(1, 990, 10);
        Utils.print(a, "排序前");
        exampleSort(a);
        Utils.print(a, "排序后");
        sort(a);
        Utils.print(a, "排序后");
    }
}