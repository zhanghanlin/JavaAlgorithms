package com.zhl.test.util.heap;

/**
 * 堆
 * @author zhanghanlin
 *
 */
public class Heap {	
	public static final int HEAD_MAX = 0;
	public static final int HEAD_MIN = 1;
	
	public int[] src;
	public int size;
	
	public Heap() {
		super();
	}
	
	public Heap(int[] src){
		this.src = src;
		this.size = src.length;
	}
	
	/**
	 * 打印元素
	 * @param s info
	 */
	public void print(String s){
		System.out.print(s + " > ");
		for (int i = 0; i < size; i++) {
			System.out.print(src[i] + " ");
		}
		System.out.println("");
	}
	
	/**
	 * 堆排序
	 * @param type
	 */
	public void headSort(int type){
		buildHeap(type);
		for (int i = size - 1; i >= 0; i--) {
			swap(0, i);
			heapify(0, i - 1,type);
		}
	}
	
	/**
	 * 根据type得到最大/最小堆
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
	 * @param i	下标
	 * @param heapSize	堆长度
	 * @param type	MAX,MIN
	 */
	public void heapify(int i,int heapSize,int type){
		int l = left(i);	//得到左孩子下标
		int r = right(i);	//得到右孩子下标
		int largest = i;
		if (type == HEAD_MAX) {
			//如果左孩子val比src[i]的val大,则将其下标存储在largest中
			if (l <= heapSize && src[l] > src[i]) {
				largest = l;
			}
			//如果右孩子val比src[i]的val大,则将其下标存储在largest中
			if (r <= heapSize && src[r] > src[largest]) {
				largest = r;
			}
		} else {
			//如果左孩子val比src[i]的val大,则将其下标存储在largest中
			if (l <= heapSize && src[l] < src[i]) {
				largest = l;
			}
			//如果右孩子val比src[i]的val大,则将其下标存储在largest中
			if (r <= heapSize && src[r] < src[largest]) {
				largest = r;
			}
		}
		//如果下标i对应的元素不为最大,则不符合最大堆性质,交换i和largest的元素
		if (largest != i) {
			swap(i, largest);
			heapify(largest, heapSize,type);
		}
	}
	
	/**
	 * 交换i和largest的元素
	 * @param i
	 * @param largest
	 */
	public void swap(int i,int largest){
		int temp = src[largest];
		src[largest] = src[i];
		src[i] = temp;
	}
	
	/**
	 * 得到父节点下标
	 * @param i
	 * @return
	 */
	public int parent(int i){
		return i/2;
	}
	
	/**
	 * 得到左孩子下标
	 * @param i
	 * @return
	 */
	public int left(int i){
		return 2 * i;
	}
	
	/**
	 * 得到右孩子下标
	 * @param i
	 * @return
	 */
	public int right(int i){
		return 2 * i + 1;
	}
	
	public static void main(String[] args) {
		int[] src = {49,38,52,44,81,97,76,13,27,65};
		Heap heap = new Heap(src);
		heap.print("原始堆");
		heap.buildHeap(HEAD_MAX);
		heap.print("最大堆");
		heap.headSort(HEAD_MAX);
		heap.print("最大堆排序");
		heap.buildHeap(HEAD_MIN);
		heap.print("最小堆");
		heap.headSort(HEAD_MIN);
		heap.print("最小堆排序");
	}
}
