package com.zhl.test.util.istack;

public class TestStack {
	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		stack.push(1);
		stack.push(4);
		stack.push(3);
		stack.push(6);
		stack.push(2);
		stack.push(9);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		stack.clear();
		System.out.println(stack.size());
	}
}
