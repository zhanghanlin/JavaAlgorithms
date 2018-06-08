package com.demo.util.heap;

import com.demo.util.StringUtils;
import com.demo.util.Utils;

/**
 * 最大优先队列Test
 *
 * @author zhanghanlin
 */
public class MaxPriorityQueueTest {

    public static void main(String[] args) {
        Integer[] arr = Utils.random(10, 100, 10);
        MaxPriorityQueue mph = new MaxPriorityQueue();
        for (Integer obj : arr) {
            if (obj != null && StringUtils.isNotBlank(obj.toString())) {
                mph.insert(obj);
            }
        }
        Utils.print(arr, "插入前");
        Utils.print(mph.getQueue(), "插入完毕");
        System.out.println("最大关键字（保留）：" + mph.maximum());
        System.out.println("最大关键字（删除）：" + mph.extractMax());
        Utils.print(mph.getQueue(), "删除后的堆");
        System.out.println("删除第四个节点：" + mph.delete(3));
        Utils.print(mph.getQueue(), "删除后的堆");
    }
}
