package com.demo.util.sort;

import com.demo.util.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 *
 * @author zhanghanlin
 */
public class QuickSort<T extends Comparable<T>> {

    public QuickSort() {
    }

    /***********************快速排序普通版本*******************************/
    /**
     * 快速排序
     * 伪代码
     * QUICKSORT(A, p, r)
     * --if p < r
     * ----q = PARTITION(A, p, r)
     * ----QUICKSORT(A, p, q - 1)
     * ----QUICKSORT(A, q + 1, r)
     *
     * @param t 需要排序的数组
     * @param p 排序开始位置
     * @param r 排序结束位置
     */
    public static <T extends Comparable<T>> T[] exampleSort(T[] t, int p, int r) {
        if (p < r) {
            int q = partition(t, p, r);
            exampleSort(t, p, q - 1);
            exampleSort(t, q + 1, r);
        }
        return t;
    }

    /**
     * 返回数组分割下标位置
     * 伪代码
     * PARTITION(A, p, r)
     * --x = A[r]
     * --i = p - 1
     * --for j = p to r - 1
     * ----if A[j] ≤ x
     * ------i = i + 1
     * ------exchange A[i] with A[j]
     * --exchange A[i + 1] with A[r]
     * --return i + 1
     *
     * @param p 排序开始位置
     * @param r 排序结束位置
     * @return
     */
    public static <T extends Comparable<T>> int partition(T[] t, int p, int r) {
        //主元,围绕该Val进行划分字数组
        T x = t[r];
        //排序开始比较的位置
        int i = p;
        for (int j = p; j < r; j++) {
            if (t[j].compareTo(x) <= 0) {
                //交换
                Utils.swap(t, i, j);
                i++;
            }
        }
        Utils.swap(t, i, r);
        return i;
    }
    /***********************快速排序普通版本*******************************/

    /***********************快速排序随机化版本*******************************/
    /**
     * 快速排序随机化版本
     * 伪代码
     * RANDOMIZED-QUICKSORT(A, p, r)
     * --if p < r
     * ----RANDOMIZED-PARTITION(A, p, r)
     * ----RANDOMIZED-QUICKSORT(A, p, q - 1)
     * ----RANDOMIZED-QUICKSORT(A, q + 1, r)
     *
     * @param t T[]
     * @param p int
     * @param r int
     */
    public static <T extends Comparable<T>> T[] randomSort(T[] t, int p, int r) {
        if (p < r) {
            int q = randomPartition(t, p, r);
            randomSort(t, p, q - 1);
            randomSort(t, q + 1, r);
        }
        return t;
    }

    /**
     * 返回数组分割下标位置
     * 伪代码
     * RANDOMIZED-PARTITION(A, p, r)
     * --i = RANDOM(p, r)
     * --exchange A[r] with A[i]
     * --return PARTITION(A, p, r)
     *
     * @param p 排序开始位置
     * @param r 排序结束位置
     * @return
     */
    public static <T extends Comparable<T>> int randomPartition(T[] t, int p, int r) {
        int i = Utils.random(p, r);
        Utils.swap(t, r, i);
        return partition(t, p, r);
    }
    /***********************快速排序随机化版本*******************************/


    /**
     * 模拟尾递归
     * 伪代码
     * TAIL-RECURSIVE-QUICKSORT(A, p, r)
     * --while p < r
     * ----// Partition and sort left subarray
     * ----q = PARTITION(A, p, r)
     * ----TAIL-RECURSIVE-QUICKSORT(A, p, q - 1)
     * ----p = q + 1
     *
     * @param p
     * @param r
     */
    public static <T extends Comparable<T>> T[] tailRecursiveSort(T[] t, int p, int r) {
        while (p < r) {
            int q = partition(t, p, r);
            tailRecursiveSort(t, p, q - 1);
            p = q + 1;
        }
        return t;
    }


    /***********************
     * 快速排序优化版本
     *******************************/
    private enum PIVOT_TYPE {
        /**
         * 待补充
         */
        FIRST,
        /**
         * 二分排序
         */
        MIDDLE,
        /**
         * 随机排序
         */
        RANDOM
    }

    private static Random RANDOM = new Random();

    private static PIVOT_TYPE type = PIVOT_TYPE.RANDOM;

    public static <T extends Comparable<T>> T[] sort(PIVOT_TYPE type, T[] t) {
        int pivot = 0;
        if (type == PIVOT_TYPE.MIDDLE) {
            pivot = t.length / 2;
        } else if (type == PIVOT_TYPE.RANDOM) {
            pivot = getRandom(t.length);
        }
        sort(pivot, 0, t.length - 1, t);
        return t;
    }

    /**
     * 排序
     *
     * @param <T>
     * @param pivotIndex 中间索引
     * @param start      开始索引
     * @param finish     结束索引
     * @param t          数组
     */
    private static <T extends Comparable<T>> void sort(int pivotIndex, int start, int finish, T[] t) {
        //主元索引
        pivotIndex += start;
        //主元
        T pivot = t[pivotIndex];
        int s = start;
        int f = finish;
        while (s <= f) {
            while (t[s].compareTo(pivot) < 0) {
                s++;
            }
            while (t[f].compareTo(pivot) > 0) {
                f--;
            }
            if (s <= f) {
                Utils.swap(t, s, f);
                s++;
                f--;
            }
        }
        if (start < f) {
            pivotIndex = getRandom((f - start) + 1);
            sort(pivotIndex, start, f, t);
        }
        if (s < finish) {
            pivotIndex = getRandom((finish - s) + 1);
            sort(pivotIndex, s, finish, t);
        }
    }

    /**
     * 得到0-length之间的一个随机val
     *
     * @param length
     * @return
     */
    private static int getRandom(int length) {
        if (length > 0) {
            if (type == PIVOT_TYPE.RANDOM) {
                return RANDOM.nextInt(length);
            } else if (type == PIVOT_TYPE.FIRST) {
                return 0;
            }
        }
        return length / 2;
    }

    public static void main(String[] args) {
        Integer[] src = Utils.random(10, 100, 10);
        System.out.println("普通版本：");
        Utils.print(src, "排序前");
        //起始索引,默认第一个0 最末索引,数组长度减一
        //开始排序
        exampleSort(src, 0, src.length - 1);
        Utils.print(src, "排序后");
        Arrays.fill(src, null);

        System.out.println("随机化版本：");
        src = Utils.random(13, 77, 10);
        Utils.print(src, "排序前");
        //开始排序
        randomSort(src, 0, src.length - 1);
        Utils.print(src, "排序后");
        Arrays.fill(src, null);

        System.out.println("尾递归实现：");
        src = Utils.random(23, 96, 10);
        Utils.print(src, "排序前");
        //开始排序
        tailRecursiveSort(src, 0, src.length - 1);
        Utils.print(src, "排序后");

        System.out.println("优化排序：");
        src = Utils.random(23, 96, 10);
        Utils.print(src, "排序前");
        //开始排序
        sort(PIVOT_TYPE.RANDOM, src);
        Utils.print(src, "排序后");
    }
}
