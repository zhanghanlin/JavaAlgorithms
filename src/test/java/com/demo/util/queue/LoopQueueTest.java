package com.demo.util.queue;

import com.demo.util.Utils;

/**
 * 循环队列（顺序结构存储实现）Test
 *
 * @author zhanghanlin
 */
public class LoopQueueTest {

    public static void main(String[] args) {
        Integer[] arr = Utils.random(10, 100, 10);
        LoopQueue<Integer> queue = new LoopQueue<>(arr[0]);
        for (Integer obj : arr) {
            queue.add(obj);
        }
        System.out.println("插入后的队列：" + queue.toString());
        System.out.println("队列大小：" + queue.length());
        System.out.println("返回队列顶元素，但不删除：" + queue.element());
        System.out.println("返回后的队列：" + queue.toString());
        System.out.println("返回队列顶元素，删除：" + queue.remove());
        System.out.println("删除后的队列：" + queue.toString());
        System.out.println("返回队列顶元素，删除：" + queue.remove());
        System.out.println("删除后的队列：" + queue.toString());
    }
}
