package com.zhl.test.util.queue;

import com.zhl.test.util.Utils;

/**
 * 队列的链式存储结构及实现
 * @author zhanghanlin
 *
 * @param <T>
 */
public class LinkQueue<T> {
	
	/**
	 * 定义一个内部类Node，Node实例代表链队列的节点
	 * @author zhanghanlin
	 *
	 */
	private class Node{
		private T data;
		private Node next;
		@SuppressWarnings("unused")
		public Node(){
			
		}
		public Node(T data,Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	//保存该链队列的头节点
	private Node front;
	//保存该链队列的尾节点
	private Node rear;
	//保存该链队列中已包含的节点数
	private int size;
	
	/**
	 * 创建空链队列
	 */
	public LinkQueue(){
		front = null;
		rear = null;
	}
	
	/**
	 * 以指定数据元素来创建链队列，该链队列只有一个元素
	 * @param t
	 */
	public LinkQueue(T t){
		front = new Node(t,null);
		//只有一个节点，front、rear都指向该节点
		rear = front;
		size++;
	}
	
	/**
	 * 返回链队列的长度
	 * @return
	 */
	public int length(){
		return size;
	}
	
	/**
	 * 判断链式队列是否为空队列
	 * @return
	 */
	public boolean empty(){
		return size == 0;
	}
	
	/**
	 * 将新元素加入队列
	 * @param t
	 */
	public void add(T t){
		//如果该链队列还是空链队列
		if (front == null) {
			front = new Node(t,null);
			//只有一个节点，front、rear都指向该节点
            rear = front;
		} else {
			Node newNode = new Node(t,null);
			//让尾节点的next指向新增的节点
			rear.next = newNode;
			//以新节点作为新的尾节点
			rear = newNode;
		}
		size++;
	}
	
	/**
	 * 删除队列front端的元素
	 * @return
	 */
	public T remove(){
		Node oldFront = front;
		front = front.next;
		oldFront.next = null;
		size--;
		return oldFront.data;
	}
	
	/**
	 * 访问链式队列中最后一个元素
	 * @return
	 */
	public T element(){
		return rear.data;
	}
	
	/**
	 * 清空链队列
	 */
	public void clear(){
		front = null;
		rear = null;
		size = 0;
	}
	
	public String toString(){
		if (empty()) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder("[");
			for (Node current = front ; current != null; current = current.next) {
				sb.append(current.data.toString() + ",");
			}
			int len = sb.length();
			return sb.delete(len - 1, len).append("]").toString();
		}
	}
	
	public static void main(String[] args) {
		Integer[] src = Utils.random(10, 100, 10);
		LinkQueue<Integer> lq = new LinkQueue<Integer>();
		for (int i = 0; i < src.length; i++) {
			lq.add(src[i]);
		}
		System.out.println("插入后的队列：" + lq.toString());
		System.out.println("队列大小：" + lq.length());
		System.out.println("返回队列顶元素，但不删除：" + lq.element());
		System.out.println("返回后的队列：" + lq.toString());
		System.out.println("返回队列顶元素，删除：" + lq.remove());
		System.out.println("删除后的队列：" + lq.toString());
		System.out.println("返回队列顶元素，删除：" + lq.remove());
		System.out.println("删除后的队列：" + lq.toString());
	}
}