package com.zhl.test.util.istack;

import java.util.Arrays;

public class ArrayStack<T> implements IStack<T> {
	
	private final int DEFAULT_SIZE = 3;
	
	private int size = 0;
	
	private int capacity = 0;

	private int top = 0;	//next
	
	private Object[] array;
	
	public ArrayStack(){
		this.capacity = DEFAULT_SIZE;
		this.array = new Object[this.capacity];
		this.size = 0;
	}
	
	public ArrayStack(int capacity){
		this.capacity = capacity;
		this.array = new Object[this.capacity];
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		// TODO Auto-generated method stub
		T t = (T)this.array[top - 1];
		this.array[top - 1] = null;
		this.size--;
		return t;
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		if (this.size < this.capacity) {
			this.array[top] = t;
			this.top++;
			this.size++;
		} else {
			enlarge();	//Add capacity
			push(t);
		}
	}
	
	public void enlarge() {
		this.capacity = this.capacity + this.DEFAULT_SIZE;
		Object[] newArray = new Object[this.capacity];
		System.arraycopy(array, 0, newArray, 0, array.length);
		Arrays.fill(array, null);
		this.array = newArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return (T)this.array[top -1];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Arrays.fill(array, null);
		this.top = 0;
		this.size = 0;
		this.capacity = DEFAULT_SIZE;
		this.array = new Object[this.capacity];
	}
	
	public int size(){
		return this.size;
	}
}
