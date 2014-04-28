package com.zhl.test.util.heap;

/**
 * 最大优先队列
 * @author zhanghanlin
 *
 */
public class MaxPriorityQuene {
	
	private final int DEFAULT_CAPACITY_SIZE = 16;
	private int capacity = DEFAULT_CAPACITY_SIZE;
	private int[] quene = new int[capacity];
	public int heapSize = 0;
	
	/** 
     * 往优先级队列出，插入一个元素 
     * 利用INCREASE-Key方法，从堆的最后增加元素 
     * 伪代码： 
     * MAX-HEAP-INSERT(A, key) 
     * 1 heap-size[A] ← heap-size[A] + 1 
     * 2 A[heap-size[A]] ← -∞ 
     * 3 HEAP-INCREASE-KEY(A, heap-size[A], key)
     * 时间复杂度：O(lg n)
     * @param value 待插入元素 
     */ 
	public void insert(int value) {
		quene[heapSize] = value;
		heapSize++;
		increaseKey(heapSize - 1,value);
	}
	
	/**
	 * 返回最大关键字
	 * @return
	 */
	public int maximum(){
		return quene[0];
	}
	
	/** 
     * 返回堆顶元素（最大值），并且将堆顶元素移除 
     * 伪代码： 
     * HEAP-EXTRACT-MAX(A) 
     * 1 if heap-size[A] < 1 
     * 2 then error "heap underflow" 
     * 3 max ← A[1] 
     * 4 A[1] ← A[heap-size[A]] 
     * 5 heap-size[A] ← heap-size[A] - 1 
     * 6 MAX-HEAPIFY(A, 1) 
     * 7 return max 
     * 时间复杂度：O(lg n), 
     * @return 
     */
	public int extractMax(){
		return delete(0);
	}
	
	/**
	 * 最大堆
	 * @param i	下标
	 */
	public void maxHeapify(int i){
		int l = left(i);	//得到左孩子下标
		int r = right(i);	//得到右孩子下标
		int largest = i;
		//如果左孩子val比src[i]的val大,则将其下标存储在largest中
		if (l <= heapSize && quene[l] > quene[i]) {
			largest = l;
		}
		//如果右孩子val比src[i]的val大,则将其下标存储在largest中
		if (r <= heapSize && quene[r] > quene[largest]) {
			largest = r;
		}
		//如果下标i对应的元素不为最大,则不符合最大堆性质,交换i和largest的元素
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest);
		}
	}
	
	/** 
     * 增加给定索引位元素的值，并重新构成MaxHeap。 
     * 新值必须大于等于原有值 
     * 伪代码： 
     * HEAP-INCREASE-KEY(A, i, key) 
     * 1 if key < A[i] 
     * 2 then error "new key is smaller than current key" 
     * 3 A[i] ← key 
     * 4 while i > 1 and A[PARENT(i)] < A[i] 
     * 5 do exchange A[i] ↔ A[PARENT(i)] 
     * 6 i ← PARENT(i) 
     * 时间复杂度：O(lg n) 
     * @param i 索引位 
     * @param value 新值 
     */ 
	public void increaseKey(int i ,int value){
		if (value < quene[i]) {
			System.err.println("newKey < oldKey");
		}
//		quene[i] = value;
		while (i > 0 && quene[parent(i)] < value) {
//			swap(i, parent(i));
			quene[i] = quene[parent(i)];
			i = parent(i);
		}
		quene[i] = value;
	}
	
	/**
	 * 删除i节点
	 * @param i
	 * @return
	 */
	public int delete(int i){
		if (heapSize - 1 < i) {
			throw new NullPointerException("IS NULL");
		}
		int val = quene[i];
		quene[i] = quene[heapSize - 1];
		heapSize--;
		maxHeapify(i);
		return val;
	}
	
	/**
	 * 交换元素
	 * @param i
	 * @param largest
	 */
	public void swap(int i, int largest) {
		// TODO Auto-generated method stub
		int temp = quene[i];
		quene[i] = quene[largest];
		quene[largest] = temp;
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
		int[] s = {49,38,52,44,81,97,76,13,27,65};
		MaxPriorityQuene mph = new MaxPriorityQuene();

		for (int i = 0; i < s.length; i++) {
			mph.insert(s[i]);
		}
//		System.out.println(mph.maximum());			
//		System.out.println(mph.extractMax());
//		System.out.println(mph.maximum());
//		System.out.println(mph.maximum());
//		System.out.println(mph.extractMax());
		System.out.println(mph.extractMax());
//		System.out.println(mph.delete(3));
		System.out.println("");
		for (int i = 0; i < mph.heapSize; i++) {
			System.out.println(mph.quene[i]);
		}
	}
}