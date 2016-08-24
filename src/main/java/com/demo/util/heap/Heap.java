package com.demo.util.heap;

import com.demo.util.Utils;

/**
 * 堆
 * @author zhanghanlin
 *
 */
public class Heap<T extends Comparable<T>> {	
	public static final int HEAD_MAX = 0;	//最大堆标识
	public static final int HEAD_MIN = 1;	//最小堆标识
	private final int DEFAULT_CAPATITY_SIZE = 16;	//默认堆大小为16
	
	private T[] src;	//堆
	private int size = 0;	//堆中的元素个数
	
	public Heap() {
		src = (T[])new Comparable[DEFAULT_CAPATITY_SIZE];
	}
	
	public Heap(int size){
		src = (T[])new Comparable[size];
	}
	
	/**
	 * insert value
	 * @param value
	 */
	public void insert(T value){
		src[size++] = value;
	}
	
	/**
	 * 堆排序
	 * 伪代码
	 * BUILDSORT(A)
	 * 	BUILD-MAX-HEAP(A)
	 * 	for i = A.length downto 2
	 * 		exchange A[1] with A[i]
	 * 		A.heap-size = A.heap-size - 1
	 * 		MAX-HEAPIFY(A, 1)
	 * @param type
	 */
	public void heapSort(int type){
		buildHeap(type);
		for (int i = size - 1; i >= 0; i--) {
			Utils.swap(src,0, i);
			heapify(0, i - 1,type);
		}
	}
	
	/**
	 * 根据type得到最大/最小堆
	 * 伪代码
	 * BUILD-MAX-HEAP(A)
	 * 	A.heap-size = A.length
	 * 	for i = [A.length / 2] downto 1
	 * 		MAX-HEAPIFY(A, i)
	 * @param type	MAX,MIN
	 */
	public void buildHeap(int type){
		int heapSize = size - 1;
		for (int i = heapSize / 2; i >= 0;i--) {
			heapify(i, heapSize, type);
		}
	}
		
	/**
	 * 最大/最小堆
	 * 伪代码
	 * MAX-HEAPIFY(A, i)
	 * 	l = LEFT(i)
	 * 	r = RIGHT(i)
	 * 	if l ≤ A.heap-size and A[l] > A[i]
	 * 		largest = l
	 * 	else largest = i
	 * 	if r ≤ A.heap-size and A[r] > A[i]
	 * 		largest = r
	 * 	if largest ≠ i
	 * 		exchange A[i] with A[largest]
	 * 		MAX-HEAPIFY(A, largest)
	 * @param i	下标
	 * @param heapSize	堆长度
	 * @param type	MAX,MIN
	 */
	public void heapify(int i,int heapSize,int type){
		int l = Utils.left(i);	//得到左孩子下标
		int r = Utils.right(i);	//得到右孩子下标
		int largest = i;
		if (type == HEAD_MAX) {
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
			Utils.swap(src,i, largest);
			heapify(largest, heapSize,type);
		}
	}
	
	public static void main(String[] args) {
		Integer[] src = Utils.random(10, 100, 10);
		Heap<Integer> heap = new Heap<Integer>(10);
		for (Integer i : src) {
			heap.insert(i);
		}
		Utils.print(heap.src,"原始堆");
		heap.buildHeap(HEAD_MAX);
		Utils.print(heap.src,"最大堆");
		heap.heapSort(HEAD_MAX);
		Utils.print(heap.src,"最大堆排序");
		heap.buildHeap(HEAD_MIN);
		Utils.print(heap.src,"最小堆");
		heap.heapSort(HEAD_MIN);
		Utils.print(heap.src,"最小堆排序");
	}
}