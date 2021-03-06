package com.demo.util.heap;

import com.demo.util.Utils;

/**
 * 最大优先队列
 *
 * @author zhanghanlin
 */
class MaxPriorityQueue {

    private final int DEFAULT_CAPACITY_SIZE = 16;
    private int capacity = DEFAULT_CAPACITY_SIZE;
    private Integer[] queue = new Integer[capacity];
    private int heapSize = 0;

    /**
     * 往优先级队列出，插入一个元素
     * 利用INCREASE-Key方法，从堆的最后增加元素
     * 伪代码：
     * MAX-HEAP-INSERT(A, key)
     * --A.heap-size = A.heap-size + 1
     * --A[A.heap-size] = -∞
     * --HEAP-INCREASE-KEY(A, A.heap-size, key)
     *
     * @param value 待插入元素
     */
    void insert(int value) {
        queue[heapSize] = value;
        heapSize++;
        increaseKey(heapSize - 1, value);
    }

    /**
     * 删除i节点
     * 伪代码：
     * HEAP-DELETE(A,i)
     * --if A.heap-size < i
     * ----error "heap underflow"
     * --max = A[i]
     * --A[i] = A[A.heap-size]
     * --A.heap-size = A.heap-size - 1
     * --MAX-HEAPIFY(A, i)
     * --return max
     *
     * @param i 节点i
     * @return 删除节点
     */
    int delete(int i) {
        if (heapSize - 1 < i) {
            throw new NullPointerException("IS NULL");
        }
        int val = queue[i];
        queue[i] = queue[heapSize - 1];
        queue[heapSize - 1] = null;
        heapSize--;
        maxHeapIfy(i);
        return val;
    }


    /**
     * 返回最大关键字
     * <p>
     * HEAP-MAXIMUM(A)
     * --return A[1]
     *
     * @return maximum
     */
    int maximum() {
        return queue[0];
    }

    /**
     * 返回堆顶元素（最大值），并且将堆顶元素移除
     * 伪代码
     * HEAP-EXTRACT-MAX(A, i)
     * --return HEAP-DELETE(A, i);
     *
     * @return extractMax
     */
    int extractMax() {
        return delete(0);
    }

    /**
     * 最大堆
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
     * @param i 下标
     */
    private void maxHeapIfy(int i) {
        //得到左孩子下标
        int l = Utils.left(i);
        //得到右孩子下标
        int r = Utils.right(i);
        int largest = i;
        //如果左孩子val比src[i]的val大,则将其下标存储在largest中
        if (l < heapSize && queue[l] > queue[i]) {
            largest = l;
        }
        //如果右孩子val比src[i]的val大,则将其下标存储在largest中
        if (r < heapSize && queue[r] > queue[largest]) {
            largest = r;
        }
        //如果下标i对应的元素不为最大,则不符合最大堆性质,交换i和largest的元素
        if (largest != i) {
            Utils.swap(queue, i, largest);
            maxHeapIfy(largest);
        }
    }

    /**
     * 增加给定索引位元素的值，并重新构成MaxHeap。
     * 新值必须大于等于原有值
     * 伪代码：
     * HEAP-INCREASE-KEY(A, i, key)
     * --if key < A[i]
     * ----error "new key is smaller than current key"
     * --A[i] = key
     * --while i > 1 and A[PARENT(i)] < A[i]
     * ----excheange A[i] with A[PARENT(i)]
     * ----i = PARENT(i)
     *
     * @param i     索引位
     * @param value 新值
     */
    private void increaseKey(int i, int value) {
        if (value < queue[i]) {
            System.err.println("newKey < oldKey");
        }
        //利用插入排序完成
        while (i > 0 && queue[Utils.parent(i)] < value) {
            queue[i] = queue[Utils.parent(i)];
            i = Utils.parent(i);
        }
        queue[i] = value;
        //交换法
//		queue[i] = value;
//		while (i > 0 && queue[Utils.parent(i)] < value) {
//			Utils.swap(queue,i, Utils.parent(i));
//			i = Utils.parent(i);
//		}
    }

    Integer[] getQueue() {
        return queue;
    }
}