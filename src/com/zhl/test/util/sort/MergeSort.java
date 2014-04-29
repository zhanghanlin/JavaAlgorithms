package com.zhl.test.util.sort;

import com.zhl.test.util.Utils;

/**
 * 归并排序
 * @author zhanghanlin
 *
 */
public class MergeSort {
	
	/**
	 * 归并排序 - 排序
	 * @param src	数组
	 * @param s	数组开始索引
	 * @param t	数组长度-1
	 */
	public static void mergeSort(Integer[] src,int s,int t) {
		if (s < t) {
			int m = (s + t) / 2;
			mergeSort(src, s, m);	//对L排序
			mergeSort(src, m + 1, t);	//对R排序
			//mergeA(src, s, m, t);
			mergeB(src, s, m, t);
		}
	}
	
	/**
	 * 归并排序 - 归并
	 * 根据索引下标直接归到一个数组中
	 * @param src	排序数组
	 * @param s	L集合开始索引
	 * @param m	R集合开始索引
	 * @param t	最后索引
	 */
	public static void mergeA(Integer[] src,int s,int m,int t) {
		int i = s , j = m + 1 , k = 0;
		Integer[] temp = new Integer[src.length];
		//将数组存放在临时的空间内
		while (i <= m && j <= t) {
			if (src[i] > src[j]) {
				temp[k++] = src[i++];
			} else {
				temp[k++] = src[j++];
			}
		}
		while (i <= m) temp[k++] = src[i++];
		while (j <= t) temp[k++] = src[j++];
		for (int x = 0; x < k; x++) src[s + x] = temp[x];
	}
	
	/**
	 * 归并排序 - 归并
	 * 分别将特定的数放入特定的数组,最后合并数组
	 * @param src	排序数组
	 * @param s	L集合开始索引
	 * @param m	R集合开始索引
	 * @param t	最后索引
	 */
	public static void mergeB(Integer[] src,int s,int m,int t) {
		//将两个序列分开存放在临时的空间内
		Integer[] left = new Integer[m - s + 1];
		Integer[] right = new Integer[t - m];
		for (int i = 0; i < left.length; i++) {
			left[i] = src[s + i];
		}		
		for (int i = 0; i < right.length; i++) {
			right[i] = src[m + i + 1];
		}
		//开始 Merge 这两个序列
		for (int k = s, i = 0, j = 0; k < t;) {
			if (left[i] <= right[j]) {
				src[k++] = left[i++];
			} else {
				src[k++] = right[j++];
			}
			//如果有一临时序列已经 Merge完，那么，另外一个将会全部取出，放在排好序的序列后面
			if (i == left.length) {
				for (int x = j; x < right.length; x++) {
					src[k++] = right[x];
				}
			}
			if (j == right.length) {
				for (int x = i; x < left.length; x++) {
					src[k++] = left[x];
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] mergeSrc = Utils.random(10, 100, 10);
		Utils.print(mergeSrc,"排序前");
		mergeSort(mergeSrc, 0, mergeSrc.length - 1);
		Utils.print(mergeSrc,"排序后");
	}
}