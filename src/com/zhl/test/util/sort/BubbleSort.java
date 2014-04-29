package com.zhl.test.util.sort;

import com.zhl.test.util.Utils;

/**
 * 冒泡排序
 * @author zhanghanlin
 *
 */
public class BubbleSort {
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