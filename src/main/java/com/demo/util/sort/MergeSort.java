package com.demo.util.sort;

import com.demo.util.Utils;

/**
 * 归并排序
 * 分解 > 解决 > 合并
 *
 * @author zhanghanlin
 */
public class MergeSort<T extends Comparable<T>> {

    public MergeSort() {
    }

    /**
     * 归并排序 - 排序
     * 伪代码
     * MERGE-SORT(A, p, r)
     * --if p < r
     * ----q = [(p + r) / 2]
     * ----MERGE-SORT(A, p, q)
     * ----MERGE-SORT(A, q + 1, r)
     * ----MERGE(A, p, q, r)
     *
     * @param t      数组
     * @param start  数组开始索引
     * @param length 数组长度-1
     */
    public static <T extends Comparable<T>> T[] exampleSort(T[] t, int start, int length) {
        if (start < length) {
            int m = (start + length) / 2;
            //对L排序
            exampleSort(t, start, m);
            //对R排序
            exampleSort(t, m + 1, length);
//			exampleMergeA(t, start, m, length);
            exampleMergeB(t, start, m, length);
        }
        return t;
    }

    /**
     * 归并排序 - 归并
     * 伪代码
     * MERGE(A, p, q, r)
     * --n1 = q - p + 1
     * --n2 = r - q
     * --let L[1..n1 + 1] and R[1..n2 + 1] be new arrays
     * --for i = 1 to n1
     * ----L[i] = A[p + i - 1]
     * --for j = 1 to n2
     * ----R[j] = A[q + j]
     * --L[n1 + 1] = ∞
     * --R[n2 + 1] = ∞
     * --i = 1
     * --j = 1
     * --for k = p to r
     * ----if L[i] ≤ R[j]
     * ------A[k] = L[i]
     * ----else A[k] = R[j]
     * ------j = j + 1
     * 分别将特定的数放入特定的数组,最后合并数组
     *
     * @param t          排序数组
     * @param leftStart  L集合开始索引
     * @param rightStart R集合开始索引
     * @param lastIndex  最后索引
     */
    public static <T extends Comparable<T>> void exampleMergeA(T[] t, int leftStart, int rightStart, int lastIndex) {
        //将两个序列分开存放在临时的空间内
        T[] left = (T[]) new Comparable[rightStart - leftStart + 1];
        T[] right = (T[]) new Comparable[lastIndex - rightStart];
        for (int i = 0; i < left.length; i++) {
            left[i] = t[leftStart + i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = t[rightStart + i + 1];
        }
        //开始 Merge 这两个序列
        for (int k = leftStart, i = 0, j = 0; k < lastIndex; ) {
            if (left[i].compareTo(right[j]) <= 0) {
                t[k++] = left[i++];
            } else {
                t[k++] = right[j++];
            }
            //如果有一临时序列已经 Merge完，那么，另外一个将会全部取出，放在排好序的序列后面
            if (i == left.length) {
                for (int x = j; x < right.length; x++) {
                    t[k++] = right[x];
                }
            }
            if (j == right.length) {
                for (int x = i; x < left.length; x++) {
                    t[k++] = left[x];
                }
            }
        }
    }

    /**
     * 归并排序 - 归并
     * 根据索引下标直接归到一个数组中
     *
     * @param t          排序数组
     * @param leftStart  L集合开始索引
     * @param rightStart R集合开始索引
     * @param lastIndex  最后索引
     */
    public static <T extends Comparable<T>> void exampleMergeB(T[] t, int leftStart, int rightStart, int lastIndex) {
        int i = leftStart, j = rightStart + 1, k = 0;
        T[] temp = (T[]) new Comparable[t.length];
        //将数组存放在临时的空间内
        while (i <= rightStart && j <= lastIndex) {
            if (t[i].compareTo(t[j]) > 0) {
                temp[k++] = t[i++];
            } else {
                temp[k++] = t[j++];
            }
        }
        while (i <= rightStart) {
            temp[k++] = t[i++];
        }
        while (j <= lastIndex) {
            temp[k++] = t[j++];
        }
        for (int x = 0; x < k; x++) {
            t[leftStart + x] = temp[x];
        }
    }

    public static <T extends Comparable<T>> T[] sort(T[] t) {
        sort(t, 0, t.length);
        return t;
    }

    /**
     * 归并排序
     *
     * @param <T>
     * @param t      数组
     * @param start  开始索引
     * @param length 数组长度
     * @return T[]
     */
    private static <T extends Comparable<T>> T[] sort(T[] t, int start, int length) {
        if (length > 2) {
            int aLength = (int) Math.floor(length / 2);
            int bLength = length - aLength;
            sort(t, start, aLength);
            sort(t, start + aLength, bLength);
            marge(t, start, aLength, start + aLength, bLength);
        } else if (length == 2) {
            T e = t[start + 1];
            if (e.compareTo(t[start]) > 0) {
                t[start + 1] = t[start];
                t[start] = e;
            }
        }
        return t;
    }

    /**
     * 归并
     *
     * @param t       数组
     * @param aStart  aStart
     * @param aLength aLength
     * @param bStart  bStart
     * @param bLength bLength
     */
    private static <T extends Comparable<T>> void marge(T[] t, int aStart, int aLength, int bStart, int bLength) {
        int count = 0;
        T[] temp = (T[]) new Comparable[aLength + bLength];
        int i = aStart;
        int j = bStart;
        int aSize = aStart + aLength;
        int bSize = bStart + bLength;
        while (i < aSize || j < bSize) {
            T a = null;
            if (i < aSize) {
                a = t[i];
            }
            T b = null;
            if (j < bSize) {
                b = t[j];
            }
            if (a != null && b == null) {
                temp[count++] = a;
                i++;
            } else if (b != null && a == null) {
                temp[count++] = b;
                j++;
            } else if (a.compareTo(b) <= 0) {
                temp[count++] = b;
                j++;
            } else {
                temp[count++] = a;
                i++;
            }
        }
        int x = 0;
        int size = aStart + aLength + bLength;
        for (int k = aStart; k < size; k++) {
            t[k] = temp[x++];
        }
    }

    public static void main(String[] args) {
        Integer[] mergeSrc = Utils.random(10, 100, 10);
        Utils.print(mergeSrc, "排序前");
        exampleSort(mergeSrc, 0, mergeSrc.length - 1);
        Utils.print(mergeSrc, "排序后");
        sort(mergeSrc);
        Utils.print(mergeSrc, "排序后");
    }
}