package com.demo.util.stack;


/**
 * 栈
 * 栈实现的是一中后进先出策略(last-in,first-out,LIFO)
 *
 * @param <T>
 * @author zhanghanlin
 */
public interface IStack<T extends Comparable<T>> {

    /**
     * 出栈
     * 伪代码
     * POP(S)
     * --if STACK-EMPTY(S)
     * ----error "underflow"
     * --else S.top = S.top - 1
     * ----return S[S.top + 1]
     *
     * @return
     */
    T pop();    //out


    /**
     * 入栈
     * 伪代码
     * PUSH(S, x)
     * --S.top = S.top + 1
     * --S[S.top] = x
     *
     * @param t
     */
    void push(T t);    //put

    /**
     * 得到第一个栈
     *
     * @return
     */
    T peek();    //get first

    /**
     * 栈的非空判断
     * 伪代码
     * STACK-EMPTY(S)
     * --if S.top == 0
     * ----return true
     * --return false
     *
     * @return
     */
    boolean empty();    //is null

    /**
     * 清空栈
     */
    void clear();
}
