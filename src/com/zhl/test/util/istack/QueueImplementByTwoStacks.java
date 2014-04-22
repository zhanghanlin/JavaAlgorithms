package com.zhl.test.util.istack;

import java.util.ArrayList;
import java.util.List;

/**
 * 用两个栈实现队列
 * @author zhanghanlin
 *
 */
public class QueueImplementByTwoStacks<T> {
	ArrayStack<T> oneStack;
	ArrayStack<T> twoStack;
	
	public QueueImplementByTwoStacks() {
		// TODO Auto-generated constructor stub
		oneStack = new ArrayStack<T>();
		twoStack = new ArrayStack<T>();
	}
	
	/**
	 * 得到最先入栈的元素
	 * @return
	 */
	public T poll(){
		T re = null;
		if (!twoStack.empty()) {
			re = twoStack.pop();
		} else {
			while (!oneStack.empty()) {
				twoStack.push(oneStack.pop());
			}
			if (!twoStack.empty()) {
				re = twoStack.pop();
			}
		}
		return re;
	}
	
	/**
	 * 入栈
	 * @param t
	 * @return
	 */
	public T offer(T t){
		oneStack.push(t);
		return t;
	}
	
	public static void main(String[] args) {
		QueueImplementByTwoStacks<Integer> qt = new QueueImplementByTwoStacks<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		qt.offer(1);
		qt.offer(3);
		qt.offer(5);
		list.add(qt.poll());
		qt.offer(2);
		list.add(qt.poll());
		qt.offer(9);
		list.add(qt.poll());
		list.add(qt.poll());
		list.add(qt.poll());
		System.out.println(list.toString());
	}
}
