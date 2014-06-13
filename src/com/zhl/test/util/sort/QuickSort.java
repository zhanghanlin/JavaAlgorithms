package com.zhl.test.util.sort;

import java.util.Arrays;

import com.zhl.test.util.Utils;

/**
 * 快速排序
 * @author zhanghanlin
 *
 */
public class QuickSort {
	public static Integer[] src = new Integer[16];
	
	/**
	 * 快速排序
	 * 伪代码
	 * QUICKSORT(A, p, r)
	 * 	if p < r
	 * 		q = PARTITION(A, p, r)
	 * 		QUICKSORT(A, p, q - 1)
	 * 		QUICKSORT(A, q + 1, r)
	 * @param src	需要排序的数组
	 * @param p	排序开始位置
	 * @param r	排序结束位置
	 */
	public static void sort(int p,int r){
		if (p < r) {
			int q = partition(p, r);
			sort(p, q - 1);
			sort(q + 1, r);
		}
	}
	
	/**
	 * 返回数组分割下标位置
	 * 伪代码
	 * PARTITION(A, p, r)
	 * 	x = A[r]
	 * 	i = p - 1
	 * 	for j = p to r - 1
	 * 		if A[j] ≤ x
	 * 			i = i + 1
	 * 			exchange A[i] with A[j]
	 * 	exchange A[i + 1] with A[r]
	 * 	return i + 1
	 * @param p	排序开始位置
	 * @param r	排序结束位置
	 * @return
	 */
	public static int partition(int p, int r){
		int x = src[r];	//主元,围绕该Val进行划分字数组
		int i = p;	//排序开始比较的位置
		for (int j = p; j < r; j++) {
			if (src[j] <= x) {
				Utils.swap(src,i, j);	//交换
				i++;
			}
		}
		Utils.swap(src,i, r);
		return i;
	}
	
	/**
	 * 快速排序随机化版本
	 * 伪代码
	 * RANDOMIZED-QUICKSORT(A, p, r)
	 * 	if p < r
	 * 		RANDOMIZED-PARTITION(A, p, r)
	 * 		RANDOMIZED-QUICKSORT(A, p, q - 1)
	 * 		RANDOMIZED-QUICKSORT(A, q + 1, r)
	 * @param p
	 * @param r
	 */
	public static void randomSort(int p, int r){
		if (p < r) {
			int q = randomPartition(p, r);
			randomSort(p, q - 1);
			randomSort(q + 1, r);
		}
	}
	
	/**
	 * 返回数组分割下标位置 - 快速排序随机化版本
	 * 伪代码
	 * RANDOMIZED-PARTITION(A, p, r)
	 * 	i = RANDOM(p, r)
	 * 	exchange A[r] with A[i]
	 * 	return PARTITION(A, p, r)
	 * @param p	排序开始位置
	 * @param r	排序结束位置
	 * @return
	 */
	public static int randomPartition(int p, int r){
		int i = Utils.random(p, r);
		Utils.swap(src, r, i);
		return partition(p, r);
	}
	
	
	/**
	 * 模拟尾递归
	 * 伪代码
	 * TAIL-RECURSIVE-QUICKSORT(A, p, r)
	 * 	while p < r
	 * 		// Partition and sort left subarray
	 * 		q = PARTITION(A, p, r)
	 * 		TAIL-RECURSIVE-QUICKSORT(A, p, q - 1)
	 * 		p = q + 1
	 * @param p
	 * @param r
	 */
	public static void tailRecursiveSort(int p, int r){
		while (p < r) {
			int q = partition(p, r);
			tailRecursiveSort(p, q - 1);
			p = q + 1;
		}
	}
	
	public static void main(String[] args) {
		src = Utils.random(10, 100, 10);
		System.out.println("普通版本：");
		Utils.print(src,"排序前");
		int p = 0;	//起始索引,默认第一个0
		int r = src.length - 1;	//最末索引,数组长度减一
		sort(p, r);	//开始排序
		Utils.print(src,"排序后");
		Arrays.fill(src, null);

		System.out.println("随机化版本：");
		src = Utils.random(13, 77, 10);
		Utils.print(src,"排序前");
		int p2 = 0;	//起始索引,默认第一个0
		int r2 = src.length - 1;	//最末索引,数组长度减一
		randomSort(p2, r2);	//开始排序
		Utils.print(src,"排序后");
		Arrays.fill(src, null);
		
		System.out.println("尾递归实现：");
		src = Utils.random(23, 96, 10);
		Utils.print(src,"排序前");
		int p3 = 0;	//起始索引,默认第一个0
		int r3 = src.length - 1;	//最末索引,数组长度减一
		tailRecursiveSort(p3, r3);	//开始排序
		Utils.print(src,"排序后");
	}
}
