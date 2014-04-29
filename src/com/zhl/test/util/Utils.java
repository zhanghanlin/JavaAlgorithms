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
	
	/**
	 * 打印输出
	 * @param o
	 */
	public static void print(Object[] o) {
		if (o != null) {
			for (int i = 0; i < o.length; i++) {
				if (isNotBlank(o[i])) {
					System.out.print(o[i].toString() + " ");
				}
			}
		}
		System.out.println("");
	}
	
	/**
	 * 打印输出
	 * @param o
	 * @param info 在输出结果前增加消息
	 */
	public static void print(Object[] o,String info){
		System.out.print(info + " : ");
		print(o);
	}
	
	/**
	 * 判断对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isNotBlank(Object o){
		return o != null && !o.toString().equals("");
	}
	
	/**
	 * 判断对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isBlank(Object o){
		return o == null || o.toString().equals("");
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
