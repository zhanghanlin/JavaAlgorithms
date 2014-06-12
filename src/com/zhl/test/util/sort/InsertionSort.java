package com.zhl.test.util.sort;

import com.zhl.test.util.Utils;

/**
 * 插入排序
 * @author zhanghanlin
 *
 */
public class InsertionSort {
	/**
	 * 插入排序
	 * @param src
	 */
	public static void insertionSort(Integer[] src) {
		for (int i = 1; i < src.length; i++) {
			if (src[i - 1] < src[i]) {
				int temp = src[i];
				int j = i;
				while (j > 0 && src[j - 1] < temp) {
					src[j] = src[j - 1] ;
					j--;
				}
				src[j] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] insertionSrc = Utils.random(10, 100, 10);
		Utils.print(insertionSrc,"排序前");
		insertionSort(insertionSrc);
		Utils.print(insertionSrc,"排序后");
	}
}