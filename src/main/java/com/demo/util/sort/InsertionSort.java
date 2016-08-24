package com.demo.util.sort;

import com.demo.util.Utils;

/**
 * 插入排序
 * 对于少量元素的排序,它是一个有效的算法
 *
 * @author zhanghanlin
 */
public class InsertionSort<T extends Comparable<T>> {

    public InsertionSort() {
    }

    /**
     * 插入排序 - 算法导论代码
     * 伪代码
     * INSERTION-SORT(A)
     * --for j = 2 to A.length
     * ----key = A[j]
     * ----//Insert A[j] into the sorted sequence A[1..j-1]
     * ----i = j - 1
     * ----while i > 0 and A[i] < key
     * ------A[i + 1] = A[i]
     * ------i = i - 1
     * ------A[i + 1] = key
     *
     * @param t
     */
    public static <T extends Comparable<T>> T[] exampleSort(T[] t) {
        for (int i = 1; i < t.length; i++) {
            if (t[i - 1].compareTo(t[i]) < 0) {
                T temp = t[i];
                int j = i;
                while (j > 0 && t[j - 1].compareTo(temp) < 0) {
                    t[j--] = t[j];
                }
                t[j] = temp;
            }
        }
        return t;
    }

    /**
     * 优化排序
     *
     * @param <T>
     * @param t
     * @return
     */
    public static <T extends Comparable<T>> T[] sort(T[] t) {
        int length = t.length;
        for (int i = 0; i < length; i++) {
            sort(t, i);
        }
        return t;
    }

    private static <T extends Comparable<T>> void sort(T[] t, int i) {
        for (int j = i; j > 0; j--) {
            T element = t[j];
            T minElement = t[j - 1];
            if (element.compareTo(minElement) < 0) {
                t[j] = minElement;
                t[j - 1] = element;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] insertionSrc = Utils.random(10, 100, 10);
        Utils.print(insertionSrc, "排序前");
        exampleSort(insertionSrc);
        Utils.print(insertionSrc, "exampleSort倒序排序");
        sort(insertionSrc);
        Utils.print(insertionSrc, "sort正序排序");
    }
}