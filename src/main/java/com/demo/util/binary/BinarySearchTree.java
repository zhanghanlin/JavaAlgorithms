package com.demo.util.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树
 *
 * @author zhanghanlin
 */
public class BinarySearchTree {
    /**
     * 根节点
     */
    private TreeNode<Integer> root = null;

    /**
     * 遍历节点列表
     */
    private List<TreeNode<Integer>> nodeList = new ArrayList<>();

    /**
     * 判断二叉查找树是否为空
     *
     * @return boolean
     */
    boolean isEmpty() {
        return root == null;
    }

    /**
     * 对于某些二叉查找树操作(比如删除关键字)来说，若树为空，则抛出异常。
     *
     * @throws Exception Tree Is Null
     */
    public void treeEmpty() throws Exception {
        if (isEmpty()) {
            throw new Exception("Tree Is Null");
        }
    }

    /**
     * 查询
     *
     * @param t Object
     * @return TreeNode
     */
    TreeNode<Integer> search(Integer t) {
        TreeNode<Integer> parentNode = root;
        while (parentNode != null && !parentNode.getObject().equals(t)) {
            if (t.compareTo(parentNode.getObject()) < 0) {
                parentNode = parentNode.getLeftNode();
            } else {
                parentNode = parentNode.getRightNode();
            }
        }
        return parentNode;
    }

    /**
     * 获取给定节点最小结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> minElemNode(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode<Integer> parentNode = node;
        while (parentNode.getLeftNode() != null) {
            parentNode = parentNode.getLeftNode();
        }
        return parentNode;
    }

    /**
     * 获取给定节点最大结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> maxElemNode(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode<Integer> parentNode = node;
        while (parentNode.getRightNode() != null) {
            parentNode = parentNode.getRightNode();
        }
        return parentNode;
    }

    /**
     * 获取给定结点在中序遍历顺序下的后继结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> successor(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        //若该结点的右子树不为空，则其后继结点就是右子树中的最小关键字结点
        if (node.getRightNode() != null) {
            return minElemNode(node.getRightNode());
        }
        //若该结点右子树为空
        TreeNode<Integer> parentNode = node.getParentNode();
        while (parentNode != null && node == parentNode.getRightNode()) {
            node = parentNode;
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    /**
     * 获取给定结点在中序遍历顺序下的前趋结点
     *
     * @param node TreeNode
     * @return TreeNode
     * @throws Exception node is null
     */
    TreeNode<Integer> precursor(TreeNode<Integer> node) throws Exception {
        if (node == null) {
            throw new Exception("node is null");
        }
        //若该结点的左子树不为空，则其前趋结点就是左子树中的最大关键字结点
        if (node.getLeftNode() != null) {
            return maxElemNode(node.getLeftNode());
        }
        //若该结点左子树为空
        TreeNode<Integer> parentNode = node.getParentNode();
        while (parentNode != null && node == parentNode.getLeftNode()) {
            node = parentNode;
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    /**
     * 插入
     *
     * @param object object
     * @throws Exception object is not exist
     */
    void insert(Integer object) throws Exception {
        TreeNode<Integer> newNode = new TreeNode<>(object);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode<Integer> rootNode = root;
        TreeNode<Integer> parentNode = null;
        while (rootNode != null) {
            parentNode = rootNode;
            if (object.compareTo(rootNode.getObject()) < 0) {
                rootNode = rootNode.getLeftNode();
            } else if (object.compareTo(rootNode.getObject()) > 0) {
                rootNode = rootNode.getRightNode();
            } else {
                throw new Exception(object + " is exist");
            }
        }
        newNode.setParentNode(parentNode);
        if (object.compareTo(parentNode.getObject()) < 0) {
            parentNode.setLeftNode(newNode);
        } else {
            parentNode.setRightNode(newNode);
        }
    }

    /**
     * 删除
     *
     * @param object object
     * @throws Exception object is not exist
     */
    void delete(Integer object) throws Exception {
        TreeNode<Integer> node = search(object);
        if (node == null) {
            throw new Exception("object is not exist!");
        }
        delete(node);
    }

    /**
     * 删除
     *
     * @param node TreeNode
     * @throws Exception Exception
     */
    private void delete(TreeNode<Integer> node) throws Exception {
        //该结点既无左孩子结点，也无右孩子结点
        TreeNode<Integer> parentNode = node.getParentNode();
        if (node.notChild()) {
            if (parentNode == parentNode.getLeftNode()) {
                parentNode.setLeftNode(null);
            } else {
                parentNode.setRightNode(null);
            }
            return;
        }
        //该结点左孩子结点为空,右孩子结点非空
        if (node.getLeftNode() == null) {
            node.getRightNode().setParentNode(parentNode);
            if (node == parentNode.getLeftNode()) {
                parentNode.setLeftNode(node.getRightNode());
            } else {
                parentNode.setRightNode(node.getRightNode());
            }
            return;
        }
        //该结点左孩子结点非空,右孩子结点为空
        if (node.getRightNode() == null) {
            node.getLeftNode().setParentNode(parentNode);
            if (node == parentNode.getLeftNode()) {
                parentNode.setLeftNode(node.getLeftNode());
            } else {
                parentNode.setRightNode(node.getLeftNode());
            }
            return;
        }
        //该结点左右孩子结点均非空,则删除该结点的后继结点,并用该后继结点取代该结点
        TreeNode<Integer> successorNode = successor(node);
        delete(successorNode);
        node.setObject(successorNode.getObject());
    }

    /**
     * 获得二叉查找树的中序遍历结点列表
     *
     * @return List List
     */
    private List<TreeNode<Integer>> inOrderTraverseList() {
        if (nodeList != null) {
            nodeList.clear();
        }
        inOrderTraverse(root);
        return nodeList;
    }

    /**
     * 对给定二叉查找树进行中序遍历
     *
     * @param root TreeNode
     */
    private void inOrderTraverse(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLeftNode());
        nodeList.add(root);
        inOrderTraverse(root.getRightNode());
    }

    /**
     * 获取二叉查找树中关键字的有序列表
     *
     * @return String
     */
    String toStringOfOrderList() {
        StringBuilder sbBuilder = new StringBuilder(" [ ");
        for (TreeNode p : inOrderTraverseList()) {
            sbBuilder.append(p.getObject());
            sbBuilder.append(" ");
        }
        sbBuilder.append("]");
        return sbBuilder.toString();
    }

    TreeNode<Integer> getRoot() {
        return root;
    }

    @Override
    public String toString() {
        StringBuilder sbBuilder = new StringBuilder("[");
        for (TreeNode node : inOrderTraverseList()) {
            sbBuilder.append(node.getObject());
            sbBuilder.append(" ");
        }
        sbBuilder.append("]");
        return sbBuilder.toString();
    }
}