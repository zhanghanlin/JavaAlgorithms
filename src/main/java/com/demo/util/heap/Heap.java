package com.demo.util.heap;

import com.demo.util.Utils;

/**
 * 堆
 *
 * @author zhanghanlin
 */
public class Heap<T extends Comparable<T>> {
    /**
     * 默认堆大小为16
     */
    private static final int DEFAULT_CAPACITY_SIZE = 16;
    /**
     * 堆
     */
    private T[] src;
    /**
     * 堆中的元素个数
     */
    private int size = 0;

    Heap() {
        this(DEFAULT_CAPACITY_SIZE);
    }

    private Heap(int size) {
        src = (T[]) new Comparable[size];
    }

    /**
     * insert value
     *
     * @param value T
     */
    void insert(T value) {
        src[size++] = value;
    }

    /**
     * 堆排序
     * 伪代码
     * BUILDSORT(A)
     * --BUILD-MAX-HEAP(A)
     * ----for i = A.length downto 2
     * ------exchange A[1] with A[i]
     * ------A.heap-size = A.heap-size - 1
     * ------MAX-HEAPIFY(A, 1)
     *
     * @param sortType SortType
     */
    void heapSort(SortType sortType) {
        buildHeap(sortType);
        for (int i = size - 1; i >= 0; i--) {
            Utils.swap(src, 0, i);
            heapIfy(0, i - 1, sortType);
        }
    }

    /**
     * 根据type得到最大/最小堆
     * 伪代码
     * BUILD-MAX-HEAP(A)
     * --A.heap-size = A.length
     * --for i = [A.length / 2] downto 1
     * ----MAX-HEAPIFY(A, i)
     *
     * @param sortType MAX,MIN
     */
    void buildHeap(SortType sortType) {
        int heapSize = size - 1;
        for (int i = heapSize / 2; i >= 0; i--) {
            heapIfy(i, heapSize, sortType);
        }
    }

    /**
     * 最大/最小堆
     * 伪代码
     * MAX-HEAPIFY(A, i)
     * --l = LEFT(i)
     * --r = RIGHT(i)
     * --if l ≤ A.heap-size and A[l] > A[i]
     * ----largest = l
     * --else largest = i
     * --if r ≤ A.heap-size and A[r] > A[i]
     * ----largest = r
     * --if largest ≠ i
     * ----exchange A[i] with A[largest]
     * ----MAX-HEAPIFY(A, largest)
     *
     * @param i        下标
     * @param heapSize 堆长度
     * @param sortType MAX,MIN
     */
    private void heapIfy(int i, int heapSize, SortType sortType) {
        //得到左孩子下标
        int l = Utils.left(i);
        //得到右孩子下标
        int r = Utils.right(i);
        int largest = i;
        if (sortType.equals(SortType.MAX)) {
            //如果左孩子val比src[i]的val大,则将其下标存储在largest中
            if (l <= heapSize && src[l].compareTo(src[i]) > 0) {
                largest = l;
            }
            //如果右孩子val比src[i]的val大,则将其下标存储在largest中
            if (r <= heapSize && src[r].compareTo(src[largest]) > 0) {
                largest = r;
            }
        } else {
            //如果左孩子val比src[i]的val大,则将其下标存储在largest中
            if (l <= heapSize && src[l].compareTo(src[i]) < 0) {
                largest = l;
            }
            //如果右孩子val比src[i]的val大,则将其下标存储在largest中
            if (r <= heapSize && src[r].compareTo(src[largest]) < 0) {
                largest = r;
            }
        }
        //如果下标i对应的元素不为最大,则不符合最大堆性质,交换i和largest的元素
        if (largest != i) {
            Utils.swap(src, i, largest);
            heapIfy(largest, heapSize, sortType);
        }
    }

    public T[] getSrc() {
        return src;
    }

    void setSrc(T[] src) {
        this.src = src;
    }

    /**
     * 排序枚举
     */
    public enum SortType {
        /**
         * 最小堆
         */
        MIN,
        /**
         * 最大堆
         */
        MAX
    }
}