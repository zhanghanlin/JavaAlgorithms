package com.zhl.test.util.queue;

import java.util.Arrays;

import com.zhl.test.util.Utils;

/**
 * 队列的顺序存储结构及实现
 * @author zhanghanlin
 *
 * @param <T>
 */
public class SequenceQueue<T> {
	private int DEFAULT_SIZE = 10;
	//数组的长度
	private int capacity;
	//定义一个数组用于保存顺序队列的元素
	private Object[] elementDate;
	//保存顺序队列中元素的当前个数
	private int front = 0;	//前
	private int rear = 0;	//后
	
	/**
	 * 以默认数组长度创建空顺序队列
	 */
	public SequenceQueue(){
		capacity = DEFAULT_SIZE;
		elementDate = new Object[capacity];
	}
	
	/**
	 * 以一个初始化元素来创建顺序队列
	 * @param t
	 */
	public SequenceQueue(T t){
		this();
		elementDate[0] = t;
		rear++;
	}
	
	/**
	 * 以指定长度的数组来创建顺序队列 
	 * @param t	指定顺序队列中第一个元素 
	 * @param initSize	指定顺序队列底层数组的长度
	 */
	public SequenceQueue(T t, int initSize){
		capacity = initSize;
		elementDate = new Object[capacity];
		elementDate[0] = t;
		rear++;
	}
	
	/**
	 * 获取顺序队列的大小
	 * @return
	 */
	public int length(){
		return rear - front;
	}
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean empty(){
		return rear == front;
	}
	
	/**
	 * 插入队列
	 * @param t
	 */
	public void add(T t){
		if (rear > capacity - 1) {
			throw new IndexOutOfBoundsException("队列已满 - 上溢");
		}
		elementDate[rear++] = t;
	}
	
	/**
	 * 移除队列
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T remove(){
		if (empty()) {
			throw new IndexOutOfBoundsException("空队列 - 下溢");
		}
		//保留队列的rear端的元素的值
		T oldValue = (T)elementDate[front];
		//释放队列的rear端的元素
		elementDate[front++] = null;
		return oldValue;
	}
	
	/**
	 * 返回队列顶元素，但不删除队列顶元素
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T element(){
		if (empty()) {
			throw new IndexOutOfBoundsException("空队列");
		}
		return (T)elementDate[front];
	}
	
	/**
	 * 清空队列
	 */
	public void clear(){
		Arrays.fill(elementDate, null);
		front = 0;
		rear = 0;
	}
	
	public String toString(){
		if (empty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (int i = front; i < rear; i++) {
			sb.append(elementDate[i].toString() + ",");
		}
		int len = sb.length();
		return sb.delete(len - 1, len).append("]").toString();
	}
	
	public static void main(String[] args) {
		Integer[] src = Utils.random(10, 100, 10);
		SequenceQueue<Integer> sq = new SequenceQueue<Integer>(src[0],src.length);
		for (int i = 1; i < src.length; i++) {
			sq.add(src[i]);
		}
		System.out.println("插入后的队列：" + sq.toString());
		System.out.println("队列大小：" + sq.length());
		System.out.println("返回队列顶元素，但不删除：" + sq.element());
		System.out.println("返回后的队列：" + sq.toString());
		System.out.println("返回队列顶元素，删除：" + sq.remove());
		System.out.println("删除后的队列：" + sq.toString());
		System.out.println("返回队列顶元素，删除：" + sq.remove());
		System.out.println("删除后的队列：" + sq.toString());
	}
}