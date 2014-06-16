package com.zhl.test.util.sort;

import java.util.Arrays;

import com.zhl.test.util.Utils;

/**
 * 计数排序
 * @author zhanghanlin
 *
 */
public class CountingSort {

	/**
	 * 计数排序
	 * 伪代码
	 * COUNTING-SORT(A, B, k)
	 * 	let C[0..k] be a new array
	 * 	for i = 0 to k
	 * 		C[i] = 0
	 * 	for j = 1 to A.length
	 * 		C[A[j]] = C[A[j]] + 1
	 * 	//C[i] now contains the number of elements equal to i.
	 * 	for i = 1 to k 
	 * 		C[i] = C[i] + C[i - 1]
	 * 	//C[i] now contains the number of elements less than or equal to i.
	 * 	for j = A.length downto 1
	 * 		B[C[A[j]]] = A[j]
	 * 		C[A[j]] = C[A[j]] - 1
	 * @param a
	 * @param b
	 * @param k
	 */
	public static void countingSort(Integer[] a,Integer[] b,int k){
		Integer[] c = new Integer[k];	//let C[0..k] be a new array
		//for i = 0 to k
		Arrays.fill(c, 0);
		for (int j = 0; j < a.length; j++) {
			c[a[j]]++;
		}
		for (int i = 1; i < k; i++) {
			c[i] = c[i] + c[i - 1];
		}
		for (int j = a.length - 1; j >= 0; j--) {
			b[--c[a[j]]] = a[j];
			c[a[j]]--;
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = Utils.random(100, 10, 10);
		Utils.print(a,"排序前");
		Integer[] b = new Integer[a.length];
		int k = a[0];
		for (int i = 1; i < a.length; i++) {
			if (k < a[i]) {
				k = a[i];
			}
		}
		countingSort(a, b, k+1);
		Utils.print(b,"排序后");
	}
}
