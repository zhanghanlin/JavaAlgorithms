package com.demo.util.heap;

import com.demo.util.Utils;

/**
 * 堆
 *
 * @author zhanghanlin
 */
public class HeapTest {

    public static void main(String[] args) {
        Integer[] src = Utils.random(10, 100, 10);
        Heap<Integer> heap = new Heap<>();
        for (Integer i : src) {
            heap.insert(i);
        }
        Utils.print(heap.getSrc(), "原始堆");
        heap.buildHeap(Heap.SortType.MAX);
        Utils.print(heap.getSrc(), "最大堆");
        heap.heapSort(Heap.SortType.MAX);
        Utils.print(heap.getSrc(), "最大堆排序");
        heap.buildHeap(Heap.SortType.MIN);
        Utils.print(heap.getSrc(), "最小堆");
        heap.heapSort(Heap.SortType.MIN);
        Utils.print(heap.getSrc(), "最小堆排序");
    }
}
