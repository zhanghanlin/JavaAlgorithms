package com.demo.util.linkedlist;

import com.demo.util.Utils;


/**
 * 双向链表 - 循环
 *
 * @author zhanghanlin
 */
public class CircularLinkedList<T extends Comparable<T>> {

    /**
     * 定义一个内部类Node，Node实例代表链表的节点
     *
     * @author zhanghanlin
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

        @Override
        public String toString() {
            Node p = prev;
            Node n = next;
            return "{" + (p == null ? "null" : p.key) + "," + key + "," + (n == null ? "null" : n.key) + "}";
        }
    }

    /**
     * 哨兵节点
     */
    private Node nil;

    /**
     * 链表元素个数
     */
    private int size = 0;

    /**
     * 链表元素个数
     *
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public CircularLinkedList() {
        nil = new Node(null, null, null);
        nil.next = nil;
        nil.prev = nil;
    }

    /**
     * 插入 by head
     * 伪代码
     * LIST-INSERT(L, x)
     * --x.next = L.nil.next
     * --L.nil.next.prev = x
     * --L.nil.next = x
     * --x.prev = L.nil
     *
     * @param t T
     */
    public void insertByHead(T t) {
        Node node = new Node(null, t, nil.next);
        if (nil.key == null) {
            nil = node;
        }
        nil.next.prev = node;
        nil.next = node;
        node.prev = nil;
        size++;
    }

    /**
     * 插入 by tail
     *
     * @param t T
     */
    public void insertByTail(T t) {
        Node node = new Node(nil.prev, t, null);
        if (nil.key == null) {
            nil = node;
        }
        nil.prev.next = node;
        nil.prev = node;
        node.next = nil;
        size++;
    }

    /**
     * 搜索
     * 伪代码
     * LIST-SEARCH(L, k)
     * --x = L.nil.next
     * --while x ≠ L.nil and x.key ≠ k
     * ----x = x.next
     * --return x
     *
     * @param t T
     * @return Node
     */
    public Node search(T t) {
        Node n = nil.next;
        while (n != nil && n.key != t) {
            n = n.next;
        }
        return n;
    }

    /**
     * 删除
     * 伪代码
     * LIST-DELETE(L, x)
     * --x.prev.next = x.next
     * --x.next.prev = x.prev
     *
     * @param n Node
     */
    public void delete(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            int count = size;
            Node current = nil.next;
            while (count-- > 0) {
                sb.append(current.toString() + ",");
                current = current.next;
            }
            int len = sb.length();
            return sb.delete(len - 1, len).append("]").toString();
        }
    }

    public static void main(String[] args) {
        Integer[] src = Utils.random(10, 100, 10);
        CircularLinkedList<Integer> lq = new CircularLinkedList<>();
        for (int i = 0; i < src.length; i++) {
            lq.insertByHead(src[i]);
        }
        System.out.println("从头插入后的链表：" + lq.toString());
        CircularLinkedList<Integer> lq2 = new CircularLinkedList<>();
        for (int i = 0; i < src.length; i++) {
            lq2.insertByTail(src[i]);
        }
        System.out.println("从尾插入后的链表：" + lq2.toString());
    }
}
