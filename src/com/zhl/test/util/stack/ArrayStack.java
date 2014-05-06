package com.zhl.test.util.stack;

import java.util.Arrays;

import com.zhl.test.util.Utils;

/**
 * 栈实现
 * @author zhanghanlin
 *
 * @param <T>
 */
public class ArrayStack<T> implements IStack<T> {
	
	private final int DEFAULT_SIZE = 3;
	
	private int size = 0;
	
	private int capacity = 0;

	private int top = 0;	//next
	
	private Object[] array;
	
	public ArrayStack(){
		capacity = DEFAULT_SIZE;
		array = new Object[capacity];
		size = 0;
	}
	
	public ArrayStack(int capacity){
		this.capacity = capacity;
		array = new Object[capacity];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		// TODO Auto-generated method stub
		T t = (T)array[top - 1];
		array[top - 1] = null;
		size--;
		top--;
		return t;
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		//判断是否需要进行数组扩容
		if (size >= capacity) {
			enlarge();	//Add capacity
		}
		array[top++] = t;
		size++;
	}
	
	/**
	 * 数组扩容
	 */
	public void enlarge() {
		capacity = capacity + DEFAULT_SIZE;
		Object[] newArray = new Object[capacity];
		System.arraycopy(array, 0, newArray, 0, array.length);
		Arrays.fill(array, null);
		array = newArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return (T)array[top -1];
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//将数组中的数据置为null, 方便GC进行回收
		Arrays.fill(array, null);
		top = 0;
		size = 0;
		capacity = DEFAULT_SIZE;
		array = new Object[capacity];
	}
	
	/**
	 * 元素个数
	 * @return
	 */
	public int size(){
		return size;
	}
	
	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		Integer[] src = Utils.random(10, 100, 10);
		for (int i = 0; i < src.length; i++) {
			stack.push(src[i]);
		}
		Utils.print(src,"原始数组");
		System.out.println("栈是否为空：" + stack.empty());
		System.out.println("得到第一个栈：" + stack.peek());
		System.out.println("弹出：" + stack.pop());
		System.out.println("弹出后得到第一个栈：" + stack.peek());		
		System.out.println("栈大小：" + stack.size());
		stack.clear();
		System.out.println("清空栈后栈的大小：" +stack.size() + ",是否为空：" + stack.empty());
	}
}
