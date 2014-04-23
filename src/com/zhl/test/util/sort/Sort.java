package com.zhl.test.util.sort;



public class Sort {

	public static void main(String[] args) {
		int[] insertionSrc = {4,8,3,45,23,78,21,45,6};
		insertionSort(insertionSrc);
		print(insertionSrc);
		
		int[] mergeSrc = {14,8,63,45,93,78,21,45,6};
		mergeSort(mergeSrc, 0, mergeSrc.length - 1);
		print(mergeSrc);
		
		int[] bubbleSrc = {14,8,63,45,93,78,21,45,6};
		bubbleSort(bubbleSrc);
		print(bubbleSrc);		
		random(1, 10);
	}
	
	/**
	 * print
	 * @param str
	 */
	public static void print(int[] str) {
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 冒泡排序
	 * @param str
	 */
	public static void bubbleSort(int[] str){
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
	
	/**
	 * 插入排序
	 * @param src
	 */
	public static void insertionSort(int[] src) {
		for (int i = 1; i < src.length; i++) {
			if (src[i - 1] < src[i]) {
				int temp = 	src[i];
				int j = i;
				while (j > 0 && src[j - 1] < temp) {
					src[j] = src[j - 1] ;
					j--;
				}
				src[j] = temp;
			}
		}
	}
	
	/**
	 * 归并排序 - 归并
	 * @param src	排序数组
	 * @param s	L集合开始索引
	 * @param m	R集合开始索引
	 * @param t	最后索引
	 */
	public static void merge(int[] src,int s,int m,int t) {
		int i = s , j = m + 1 , k = 0;
		int[] temp = new int[src.length];
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
	 * 归并排序 - 归并2
	 * @param src	排序数组
	 * @param s	L集合开始索引
	 * @param m	R集合开始索引
	 * @param t	最后索引
	 */
	public static void merge2(int[] src,int s,int m,int t) {
		//将两个序列分开存放在临时的空间内
		int[] left = new int[m - s + 1];
		int[] right = new int[t - m];
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
	
	/**
	 * 归并排序 - 排序
	 * @param src	数组
	 * @param s	数组开始索引
	 * @param t	数组长度-1
	 */
	public static void mergeSort(int[] src,int s,int t) {
		if (s < t) {
			int m = (s + t) / 2;
			mergeSort(src, s, m);	//对L排序
			mergeSort(src, m + 1, t);	//对R排序
			//merge(src, s, m, t);	//归并
			merge2(src, s, m, t);
		}
	}
	
	
	/**
	 * 得到一个介于a和b之间的随机数
	 * @param a
	 * @param b
	 * @return
	 */
	public static int random(int a ,int b){
		return a + new Double(Math.random() * (b - a)).intValue();
	}
}