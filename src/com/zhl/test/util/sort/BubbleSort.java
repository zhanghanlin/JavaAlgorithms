package com.zhl.test.util.sort;

import com.zhl.test.util.Utils;

/**
 * 冒泡排序
 * 	流行但低效
 * @author zhanghanlin
 *
 */
public class BubbleSort {
	
	/**
	 * 伪代码
	 * BUBBLESORT(A)
	 * 	for i = 1 to A.length - 1
	 * 		for j = A.length down to i + 1
	 * 			if A[j] < A[j - 1]
	 * 				exchange A[j] with A[j - 1]
	 * 
	 */
	
	/**
	 * 冒泡排序
	 * @param str
	 */
	public static void bubbleSort(Integer[] str){
		int temp = 0;
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str.length - i - 1; j++) {
				if (str[j] < str[j + 1]) {
					temp = str[j];
					str[j] = str[j + 1];
					str[j + 1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] bubbleSrc = Utils.random(10, 100, 10);
		Utils.print(bubbleSrc,"排序前");
		bubbleSort(bubbleSrc);
		Utils.print(bubbleSrc,"排序后");
	}
}