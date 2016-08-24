package com.demo.util.stack;

import com.demo.util.Utils;

import java.util.Arrays;

/**
 * 栈实现
 *
 * @param <T>
 * @author zhanghanlin
 */
public class ArrayStack<T extends Comparable<T>> implements IStack<T> {

    /**
     * 栈默认大小
     */
    private final int DEFAULT_SIZE = 3;

    /**
     * 栈内元素个数
     */
    private int size = 0;

    /**
     * 栈总大小
     */
    private int capacity = 0;

    /**
     * 最后一个元素坐标
     */
    private int top = 0;

    /**
     * 栈数组
     */
    private T[] array;

    /**
     * 默认无参构造
     * 默认创建一个默认长度的栈
     */
    public ArrayStack() {
        capacity = DEFAULT_SIZE;
        array = (T[]) new Comparable[capacity];
        size = 0;
    }

    /**
     * 创建指定大小的栈
     *
     * @param capacity
     */
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Comparable[capacity];
        size = 0;
    }

    @Override
    public T pop() {
        T t = array[--top];
        size--;
        return t;
    }

    @Override
    public void push(T t) {
        //判断是否需要进行数组扩容
        if (size >= capacity) {
            enlarge();    //Add capacity
        }
        array[top++] = t;
        size++;
    }

    /**
     * 数组扩容
     */
    public void enlarge() {
        capacity = capacity + DEFAULT_SIZE;
        T[] newArray = (T[]) new Comparable[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        Arrays.fill(array, null);
        array = newArray;
    }

    /**
     * 获取第一个栈(不出栈)
     */
    @Override
    public T peek() {
        return array[top - 1];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * 清空栈
     */
    @Override
    public void clear() {
        //将数组中的数据置为null, 方便GC进行回收
        Arrays.fill(array, null);
        top = 0;
        size = 0;
        capacity = DEFAULT_SIZE;
        array = (T[]) new Comparable[capacity];
    }

    /**
     * 栈内的元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Override toString()
     */
    public String toString() {
        String o = "[";
        if (!empty()) {
            for (int i = 0; i < size; i++) {
                o += array[i] + ",";
            }
        }
        o = o.substring(0, o.length() - 1);
        o += "]";
        return o;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        Integer[] src = Utils.random(10, 100, 10);
        for (int i = 0; i < src.length; i++) {
            stack.push(src[i]);
        }
        Utils.print(src, "原始数组");
        System.out.println("栈的大小：" + stack.size() + ",是否为空：" + stack.empty());
        System.out.println("得到第一个栈：" + stack.peek());
        System.out.println("弹出：" + stack.pop());
        System.out.println("弹出后得到第一个栈：" + stack.peek());
        System.out.println("弹出后栈：" + stack.toString());
        System.out.println("栈大小：" + stack.size());
        stack.clear();
        System.out.println("清空栈后栈的大小：" + stack.size() + ",是否为空：" + stack.empty());
    }
}
