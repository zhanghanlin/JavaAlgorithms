package com.zhl.test.util.sort;

import java.util.Arrays;

import com.zhl.test.util.Utils;

/**
 * 基数排序
 * @author zhanghanlin
 *
 */
public class RadixSort {

	/**
	 * 基数
	 */
	public static final int RADIX = 10;
	
	/**
	 * 基数排序 - 基于计数排序
	 * 伪代码
	 * @param src
	 * @param d 位数
	 */
	public static void radixSort(Integer[] src, int d){
		int length = src.length;
		Integer[] temp = new Integer[length];	//用于暂存元素
		Integer[] count = new Integer[length];	//用于计数排序
		int divide = 1;	//初始化倍数基数为1
		for (int i = 0; i < d; i++) {
			System.arraycopy(src, 0, temp, 0, length);
			Arrays.fill(count, 0);
			
			for (int j = 0; j < length; j++) {
				count[(temp[j] / divide) % RADIX]++;
			}
			
			for (int j = 1; j < RADIX; j++) {
				count[j] += count[j - 1];
			}
			
			for (int j = length - 1; j >= 0; j--) {
				int tempKey = (temp[j] / divide) % RADIX;
				count[tempKey]--;
				src[count[tempKey]] = temp[j];
			}
			
			divide *= RADIX;
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = Utils.random(1, 990, 10);
		Utils.print(a,"排序前");
		radixSort(a, 3);
		Utils.print(a,"排序后");
	}
}
