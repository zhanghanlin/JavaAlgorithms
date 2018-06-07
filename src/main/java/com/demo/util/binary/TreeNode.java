package com.demo.util.binary;

import com.alibaba.fastjson.JSONObject;

/**
 * 二叉树节点
 *
 * @author zhanghanlin6
 */
public class TreeNode<T> {

    private T object;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;
    private TreeNode<T> parentNode;


    TreeNode(T object) {
        this(object, null, null, null);
    }

    private TreeNode(T object, TreeNode<T> leftNode, TreeNode<T> rightNode, TreeNode<T> parentNode) {
        this.object = object;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parentNode = parentNode;
    }

    /**
     * 不存在子节点
     *
     * @return boolean
     */
    boolean notChild() {
        return rightNode == null && leftNode == null;
    }

    T getObject() {
        return object;
    }

    void setObject(T object) {
        this.object = object;
    }

    TreeNode<T> getLeftNode() {
        return leftNode;
    }

    void setLeftNode(TreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    TreeNode<T> getRightNode() {
        return rightNode;
    }

    void setRightNode(TreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    TreeNode<T> getParentNode() {
        return parentNode;
    }

    void setParentNode(TreeNode<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        if (leftNode != null) {
            builder.append(JSONObject.toJSONString(leftNode.getObject()));
            builder.append(",");
        } else {
            builder.append("NIL,");
        }
        if (object != null) {
            builder.append(JSONObject.toJSONString(object));
            builder.append(",");
        } else {
            builder.append("NIL,");
        }
        if (rightNode != null) {
            builder.append(JSONObject.toJSONString(rightNode.getObject()));
        } else {
            builder.append("NIL");
        }
        builder.append(")");
        return builder.toString();
    }
}
