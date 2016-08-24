package com.demo.util.sort;

import com.demo.util.Utils;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author zhanghanlin
 */
public class CountingSort {

    /**
     * 计数排序
     * 伪代码
     * COUNTING-SORT(A, B, k)
     * --let C[0..k] be a new array
     * --for i = 0 to k
     * ----C[i] = 0
     * --for j = 1 to A.length
     * ----C[A[j]] = C[A[j]] + 1
     * --//C[i] now contains the number of elements equal to i.
     * --for i = 1 to k
     * ----C[i] = C[i] + C[i - 1]
     * --//C[i] now contains the number of elements less than or equal to i.
     * --for j = A.length downto 1
     * ----B[C[A[j]]] = A[j]
     * ----C[A[j]] = C[A[j]] - 1
     *
     * @param a
     * @param b
     * @param k
     */
    public static void exampleSort(Integer[] a, Integer[] b, int k) {
        Integer[] c = new Integer[k];    //let C[0..k] be a new array
        //for i = 0 to k
        Arrays.fill(c, 0);
        for (int j = 0; j < a.length; j++) {
            c[a[j]]++;
        }
        for (int i = 1; i < k; i++) {
            c[i] = c[i] + c[i - 1];
        }
        for (int j = a.length - 1; j >= 0; j--) {
            b[--c[a[j]]] = a[j];
            c[a[j]]--;
        }
    }

    /**
     * 优化排序
     *
     * @param t
     * @return
     */
    public static Integer[] sort(Integer[] t) {
        int maxValue = findMax(t);
        int[] counts = new int[maxValue + 1];
        updateCounts(t, counts);
        populateCounts(t, counts);
        return t;
    }

    /**
     * 根据Counts,将对于的Val放入t
     *
     * @param t
     * @param counts
     */
    private static void populateCounts(Integer[] t, int[] counts) {
        int index = 0;
//		for (int i = 0; i < counts.length; i++) {
//			int e = counts[i];
//			while (e > 0) {
//				t[index++] = i;
//				e--;
//			}
//		}
        for (int i = counts.length - 1; i > 0; i--) {
            int e = counts[i];
            while (e > 0) {
                t[index++] = i;
                e--;
            }
        }
    }

    /**
     * 更新Counts数组的Val
     *
     * @param t
     * @param counts
     */
    private static void updateCounts(Integer[] t, int[] counts) {
        for (int i : t) {
            counts[i]++;
        }
    }

    /**
     * 得到t中最大的Val
     *
     * @param t
     * @return
     */
    private static int findMax(Integer[] t) {
        int max = Integer.MIN_VALUE;
        for (int i : t) {
            if (i > max)
                max = i;
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] a = Utils.random(100, 10, 10);
        Utils.print(a, "排序前");
        Integer[] b = new Integer[a.length];
        exampleSort(a, b, findMax(a) + 1);
        Utils.print(b, "exampleSort正序排序");
        sort(a);
        Utils.print(a, "sort倒序排序");
    }
}
