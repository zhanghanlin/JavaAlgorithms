package com.zhl.test.util.stack;


/**
 * 栈
 * 	栈实现的是一中后进先出策略(last-in,first-out,LIFO)
 * @author zhanghanlin
 *
 * @param <T>
 */
public interface IStack<T> {
	
	/**
	 * 出栈
	 * @return
	 */
	public T pop();	//out
	
	/**
	 * 入栈
	 * @param t
	 */
	public void push(T t);	//put
	
	/**
	 * 得到第一个栈
	 * @return
	 */
	public T peek();	//get first
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean empty();	//is null
	
	/**
	 * 情况栈
	 */
	public void clear();
}
