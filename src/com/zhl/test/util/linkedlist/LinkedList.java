package com.zhl.test.util.linkedlist;

import com.zhl.test.util.Utils;


/**
 * 双向链表 - 非循环
 * @author zhanghanlin
 *
 * @param <T>
 */
public class LinkedList<T> {

	/**
	 * 定义一个内部类Node，Node实例代表链表的节点
	 * @author zhanghanlin
	 *
	 */
	public class Node {
		private T key;
		private Node prev;
		private Node next;
		public Node() {
			
		}
		
		public Node(Node prev, T key, Node next) {
			this.prev = prev;
			this.key = key;
			this.next = next;
		}
		
		public String toString() {
			Node p = prev;
			Node n = next;
			return "{" + (p == null ? "null" : p.key) + "," + key + "," + (n == null ? "null" : n.key) + "}";
		}
	}
	
	/**
	 * 头节点
	 */
	private Node head;
	
	/**
	 * 尾节点
	 */
	private Node tail;
	
	/**
	 * 链表元素个数
	 */
	private int size;
	
	/**
	 * 新的空链表
	 */
	public LinkedList() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * 链表元素个数
	 * @return
	 */
	public int length() {
		return size;
	}
	
	/**
	 * 判断链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 插入 by head
	 * 伪代码
	 * LIST-INSERT(L, x)
	 * 	x.next = L.head
	 * 	if L.head ≠ NIL
	 * 		L.head.prev = x
	 * 	L.head = x
	 * 	x.prev = NIL
	 * @param t
	 */
	public void insertByHead(T t) {
		if (isEmpty()) {
			head = new Node(null, t, null);
			tail = head;
		} else {
			Node node = new Node(null, t, head);
			head.prev = node;
			head = node;
		}
		size++;
	}
	
	/**
	 * 插入 by tail
	 * @param t
	 */
	public void insertByTail(T t) {
		if (isEmpty()) {
			head = new Node(null, t, null);
			tail = head;
		} else {
			Node node = new Node(tail, t, null);
			tail.next = node;
			tail = node;
		}
		size++;
	}
	
	/**
	 * 搜索
	 * 伪代码
	 * LIST-SEARCH(L, k)
	 * 	x = L.head
	 * 	while x ≠ NIL and x.key ≠ k
	 * 		x = x.next
	 * 	return x
	 * @param t
	 * @return
	 */
	public Node search(T t) {
		Node n = null;
		if (isEmpty()) {
			System.out.println("Null Error");
		} else {
			n = head;
			while (n != null && n.key != t) n = n.next;
		}
		return n;
	}
	
	/**
	 * 删除
	 * 伪代码
	 * LIST-DELETE(L, x)
	 * 	if x.prev ≠ NIL
	 * 		x.prev.next = x.next
	 * 	else L.head = x.next
	 * 	if x.next ≠ NIL
	 * 		x.next.prev = x.prev
	 * @param t
	 */
	public void delete(Node n) {
		if (isEmpty()) {
			System.out.println("Null Error");
		} else {
			if (n != null) {
				if (n.prev != null) {
					n.prev.next = n.next;
				} else {
					head = n.next;
				}
				if (n.next != null) {
					n.next.prev = n.prev;
				}
			}
		}
	}
	
	/**
	 * 清空链表
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public String toString(){
		if (isEmpty()) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder("[");
			for (Node current = head ; current != null; current = current.next) {
				sb.append(current.toString() + ",");
			}
			int len = sb.length();
			return sb.delete(len - 1, len).append("]").toString();
		}
	}
	
	public static void main(String[] args) {
		Integer[] src = Utils.random(10, 100, 10);
		LinkedList<Integer> lq = new LinkedList<Integer>();
		for (int i = 0; i < src.length; i++) {
			lq.insertByHead(src[i]);
		}
		System.out.println("从头插入后的链表：" + lq.toString());
		lq.clear();
		for (int i = 0; i < src.length; i++) {
			lq.insertByTail(src[i]);
		}
		System.out.println("从尾插入后的链表：" + lq.toString());
	}
}
