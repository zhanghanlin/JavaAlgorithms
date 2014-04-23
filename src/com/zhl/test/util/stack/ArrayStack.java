package com.zhl.test.util.stack;

import java.util.Arrays;

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
	
	public int size(){
		return size;
	}
	
	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		stack.push(1);
		stack.push(4);
		stack.push(3);
		stack.push(6);
		stack.push(2);
		stack.push(9);
		System.out.println(stack.empty());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		stack.clear();
		System.out.println(stack.size());
	}
}
