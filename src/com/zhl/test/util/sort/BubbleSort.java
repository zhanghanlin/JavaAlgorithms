package com.zhl.test.util.sort;
import com.zhl.test.util.Utils;

/**
 * 冒泡排序
 * 	流行但低效
 * @author zhanghanlin
 *
 */
public class BubbleSort<T extends Comparable<T>> {
	
	public BubbleSort() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 冒泡排序 - 算法导论代码
	 * 伪代码
	 * BUBBLESORT(A)
	 * 	for i = 1 to A.length - 1
	 * 		for j = A.length down to i + 1
	 * 			if A[j] < A[j - 1]
	 * 				exchange A[j] with A[j - 1]
	 * @param t
	 */
	public static <T extends Comparable<T>> T[] exampleSort(T[] t){
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length - i - 1; j++) {
				//>:倒序	<:正序
				if (t[j].compareTo(t[j + 1]) > 0){
					Utils.swap(t, j, j + 1);
				}
			}
		}
		return t;
	}
	
	/**
	 * 优化排序
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T extends Comparable<T>> T[] sort(T[] t) {
		boolean swap = true;
		int length = t.length;
		while (swap) {
			swap = false;
			for (int i = 1; i < length; i++) {
				//>:倒序	<:正序 
				if (t[i].compareTo(t[i - 1]) > 0) {
					Utils.swap(t, i, i - 1);
					swap = true;
				}
			}
			length--;
		}
		return t;
	}
	
	public static void main(String[] args) {
		Integer[] bubbleSrc = Utils.random(10, 100, 10);
		Utils.print(bubbleSrc,"排序前");
		exampleSort(bubbleSrc);
		Utils.print(bubbleSrc,"exampleSort正序排序");		
		sort(bubbleSrc);
		Utils.print(bubbleSrc,"sort倒序排序");
	}
}