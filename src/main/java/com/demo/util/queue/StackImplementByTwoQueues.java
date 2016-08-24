package com.demo.util.queue;

/**
 * 用两个队列实现一个栈
 *
 * @param <T>
 * @author zhanghanlin
 */
public class StackImplementByTwoQueues<T extends Comparable<T>> {

    LinkQueue<T> oneQueue;
    LinkQueue<T> twoQueue;

    public StackImplementByTwoQueues() {
        oneQueue = new LinkQueue<>();
        twoQueue = new LinkQueue<>();
    }

    public boolean empty() {
        return oneQueue.empty() && twoQueue.empty();
    }

    public T pop() {
        T t = null;
        if (empty()) {
            return null;
        }
        if (twoQueue.empty()) {
            while (!oneQueue.empty()) {
                t = oneQueue.remove();
                if (!oneQueue.empty()) {
                    twoQueue.add(t);
                }
            }
        } else if (oneQueue.empty()) {
            while (!twoQueue.empty()) {
                t = twoQueue.remove();
                if (!twoQueue.empty()) {
                    oneQueue.add(t);
                }
            }
        }
        return t;
    }

    public void push(T t) {
        if (empty()) {
            oneQueue.add(t);
        }
        if (!oneQueue.empty()) {
            oneQueue.add(t);
        }
        if (!twoQueue.empty()) {
            twoQueue.add(t);
        }
    }

    public static void main(String[] args) {
        StackImplementByTwoQueues<Integer> stack = new StackImplementByTwoQueues<Integer>();
        Integer tmp;
        stack.push(1);
        stack.push(2);
        stack.push(3);
        tmp = stack.pop();
        System.out.println(tmp);//3  
        stack.push(4);
        tmp = stack.pop();
        System.out.println(tmp);//4  
        tmp = stack.pop();
        System.out.println(tmp);//2
        stack.push(5);
        stack.push(6);
        tmp = stack.pop();
        System.out.println(tmp);//6  
        tmp = stack.pop();
        System.out.println(tmp);//5  
        tmp = stack.pop();
        System.out.println(tmp);//1	
        stack.push(13);
    }
}