package com.demo.util.sort;

import com.demo.util.Utils;

/**
 * 期望为线性时间的选择算法,选择数组从p到r之间,第i个最小值
 *
 * @author zhanghanlin
 */
public class RandomizedSelect<T extends Comparable<T>> {

    /**
     * 期望为线性时间的选择算法
     * 选择数组从p到r之间,第i个最小值
     * 伪代码
     * RANDOMIZED-SELECT(A, p, r, i)
     * --if p == r
     * ----return A[p]
     * --q = RANDOMIZED-PARTITION(A, p, r)
     * --k = q - p + 1
     * --if i == k	//the pivot value is the answer
     * ----return A[q]
     * --else if i < k
     * ----return RANDOMIZED-SELECT(A, p, q - 1, i)
     * --else return RANDOMIZED-SELECT(A, q + 1, r, i - k)
     *
     * @param t
     * @param p 起始索引
     * @param r 结束索引
     * @param i 最小索引
     */
    public static <T extends Comparable<T>> T randomSelect(T[] t, int p, int r, int i) {
        if (i < 1) {
            i = 1;
        }
        if (p == r) {
            return t[p];
        }
        int q = QuickSort.randomPartition(t, p, r);
        int k = q - p + 1;
        if (i == k) {
            return t[q];
        } else if (i < k) {
            return randomSelect(t, p, q - 1, i);
        } else {
            return randomSelect(t, q + 1, r, i - k);
        }
    }

    /**
     * 获得数组内最小值
     * 伪代码
     * MINIMUN(A)
     * --min = A[1]
     * --for i = 2 to A.length
     * ----if min > A[i]
     * ------min = A[i]
     * --return min
     *
     * @param src
     * @return
     */
    public static Integer minimum(Integer[] src) {
        int min = src[0];
        for (int i = 1; i < src.length; i++) {
            if (min > src[i]) {
                min = src[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Integer[] src = Utils.random(10, 100, 10);
        Utils.print(src, "原始数组");
        int i = 1;
        Integer p = randomSelect(src, 0, src.length - 1, i);
        System.out.println("第" + i + "个最小值:" + p);
    }
}