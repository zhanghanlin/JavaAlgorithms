package com.demo.util.queue;

import com.demo.util.StringUtils;

import java.util.Arrays;

/**
 * 循环队列（顺序结构存储实现）
 *
 * @param <T>
 * @author zhanghanlin
 */
class LoopQueue<T extends Comparable<T>> {

    private static final int DEFAULT_SIZE = 1 << 4;
    /**
     * 数组的长度
     */
    private int capacity;
    /**
     * 定义一个数组用于保存顺序队列的元素
     */
    private T[] elementDate;
    /**
     * 保存顺序队列中元素的当前个数
     */
    private int front = 0;
    private int rear = 0;

    /**
     * 以默认数组长度创建空顺序队列
     */
    private LoopQueue() {
        capacity = DEFAULT_SIZE;
        elementDate = (T[]) new Comparable[capacity];
    }

    /**
     * 以一个初始化元素来创建顺序队列
     *
     * @param t T
     */
    LoopQueue(T t) {
        this(t, DEFAULT_SIZE);
    }

    /**
     * 以指定长度的数组来创建顺序队列
     *
     * @param t        指定顺序队列中第一个元素
     * @param initSize 指定顺序队列底层数组的长度
     */
    private LoopQueue(T t, int initSize) {
        capacity = initSize;
        elementDate = (T[]) new Comparable[capacity];
        elementDate[0] = t;
        rear++;
    }

    /**
     * 获取队列大小
     *
     * @return length
     */
    public int length() {
        if (empty()) {
            return 0;
        }
        return rear > front ? rear - front : capacity - (front - rear);
    }

    /**
     * 判断是否为空
     * rear==front且rear处的元素为null
     *
     * @return boolean
     */
    boolean empty() {
        return rear == front && elementDate[rear] == null;
    }

    /**
     * 插入队列
     * 如果rear已经到头，那就转头
     *
     * @param t T
     */
    void add(T t) {
        if (rear == front && elementDate[front] != null) {
            throw new IndexOutOfBoundsException("队列已满 - 上溢");
        }
        elementDate[rear++] = t;
        rear = rear == capacity ? 0 : rear;
    }

    /**
     * 删除队列
     *
     * @return T
     */
    T remove() {
        if (empty()) {
            throw new IndexOutOfBoundsException("空队列 - 下溢");
        }
        //保留队列的rear端的元素的值
        T oldValue = elementDate[front];
        //释放队列的rear端的元素
        elementDate[front++] = null;
        //如果front已经到头，那就转头
        front = front == capacity ? 0 : front;
        return oldValue;
    }

    /**
     * 返回队列顶元素，但不删除队列顶元素
     *
     * @return T
     */
    T element() {
        if (empty()) {
            throw new IndexOutOfBoundsException("空队列");
        }
        return elementDate[front];
    }

    /**
     * 清空循环队列
     */
    void clear() {
        Arrays.fill(elementDate, null);
        front = 0;
        rear = 0;
    }

    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        }
        //如果front < rear，有效元素就是front到rear之间的元素
        if (front < rear) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementDate[i].toString()).append(",");
            }
            int len = sb.length();
            return sb.delete(len - 1, len).append("]").toString();
        } else {    //如果front >= rear，有效元素为front->capacity之间、0->front之间的
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < capacity; i++) {
                sb.append(elementDate[i].toString()).append(",");
            }
            for (int i = 0; i < front; i++) {
                if (elementDate[i] != null && StringUtils.isNotBlank(elementDate[i].toString())) {
                    sb.append(elementDate[i].toString()).append(",");
                }
            }
            int len = sb.length();
            return sb.delete(len - 1, len).append("]").toString();
        }
    }
}