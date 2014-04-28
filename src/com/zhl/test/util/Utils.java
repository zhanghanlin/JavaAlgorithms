package com.zhl.test.util;

/**
 * 公共类
 * @author zhanghanlin
 *
 */
public class Utils {

	/**
	 * 交换数组t中下标为i和j的位置
	 * @param t	数组对象
	 * @param i	下标A
	 * @param j	下标B
	 */
	public static void swap(Object[] t,int i,int j){
		Object temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}
	
	/**
	 * 得到父节点下标
	 * @param i
	 * @return
	 */
	public static int parent(int i){
		return i/2;
	}
	
	/**
	 * 得到左孩子下标
	 * @param i
	 * @return
	 */
	public static int left(int i){
		return 2 * i;
	}
	
	/**
	 * 得到右孩子下标
	 * @param i
	 * @return
	 */
	public static int right(int i){
		return 2 * i + 1;
	}
}
