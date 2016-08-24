package com.demo.util.sort;

import com.demo.util.Utils;

/**
 * 堆排序
 *
 * @param <T>
 * @author zhanghanlin
 */
public class HeapSort<T extends Comparable<T>> {

    public HeapSort() {
    }

    /**
     * 堆排序
     *
     * @param <T>
     * @param t
     * @return
     */
    public static <T extends Comparable<T>> T[] sort(T[] t) {
        createHeap(t);
        sortHeap(t);
        return t;
    }

    private static <T extends Comparable<T>> void sortHeap(T[] t) {
        int length = t.length;
        for (int index = length - 1; index > 0; index--) {
            Utils.swap(t, 0, index);
            int i = 0;
            while (true) {
                int left = Utils.left(i);
                int right = Utils.right(i);
                if (left >= index) break;
                if (right >= index) {
                    if (t[left].compareTo(t[index]) > 0) {
                        Utils.swap(t, left, i);
                    }
                    break;
                }
                T iElement = t[i];
                T leftElement = t[left];
                T rightElement = t[right];
                if (leftElement.compareTo(iElement) > 0) {
                    if (leftElement.compareTo(rightElement) > 0) {
                        Utils.swap(t, i, left);
                        i = left;
                        continue;
                    }
                    Utils.swap(t, i, right);
                    i = right;
                    continue;
                }
                if (rightElement.compareTo(iElement) > 0) {
                    Utils.swap(t, i, right);
                    i = right;
                    continue;
                }
                break;
            }
        }
    }

    /**
     * 创建堆
     *
     * @param <T>
     * @param t
     */
    private static <T extends Comparable<T>> void createHeap(T[] t) {
        int size = 0;
        for (T e : t) {
            size = add(e, size, t);
        }
    }

    /**
     * 将元素添加到堆中
     *
     * @param <T>
     * @param element
     * @param size
     * @param t
     * @return
     */
    private static <T extends Comparable<T>> int add(T element, int size, T[] t) {
        int i = size;
        t[size++] = element;
        T e = t[i];
        int parentIndex = Utils.parent(i);
        T parent = t[parentIndex];
        while (e.compareTo(parent) > 0) {
            Utils.swap(t, i, parentIndex);
            i = parentIndex;
            e = t[i];
            parentIndex = Utils.parent(i);
            parent = t[parentIndex];
        }
        return size;
    }

    public static void main(String[] args) {
        Integer[] src = Utils.random(10, 100, 10);
        Utils.print(src, "排序前");
        sort(src);
        Utils.print(src, "排序后");
    }
}