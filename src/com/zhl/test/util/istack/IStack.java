package com.zhl.test.util.istack;

public interface IStack<T> {
	
	public T pop();	//out
	
	public void push(T t);	//put
	
	public T peek();	//get first
	
	public boolean isEmpty();	//is null
	
	public void clear();
}
